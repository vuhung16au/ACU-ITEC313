import re
from pathlib import Path

base_dir = Path('.')

for pom_path in base_dir.rglob('pom.xml'):
    content = pom_path.read_text()
    
    def add_version(match):
        dep = match.group(0)
        if '<version>' not in dep:
            return dep.replace('</artifactId>', '</artifactId>\n            <version>1.19.7</version>')
        return dep
        
    new_content = re.sub(r'<dependency>\s*<groupId>org\.testcontainers</groupId>.*?</dependency>', add_version, content, flags=re.DOTALL)
    
    if new_content != content:
        pom_path.write_text(new_content)
        print(f"Updated {pom_path}")
