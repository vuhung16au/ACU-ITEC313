import re
from pathlib import Path

base_dir = Path('.')

for makefile_path in base_dir.rglob('Makefile'):
    content = makefile_path.read_text()
    new_content = content.replace('-DskipTests', '-Dmaven.test.skip=true')
    
    if new_content != content:
        makefile_path.write_text(new_content)
        print(f"Updated {makefile_path}")
