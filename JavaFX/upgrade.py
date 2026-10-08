import os
import re

# 1. Update Root POM
root_pom = "pom.xml"
with open(root_pom, "r") as f: content = f.read()

content = re.sub(r'<maven\.compiler\.source>.*?</maven\.compiler\.source>', '<maven.compiler.source>27</maven.compiler.source>', content)
content = re.sub(r'<maven\.compiler\.target>.*?</maven\.compiler\.target>', '<maven.compiler.target>27</maven.compiler.target>', content)
content = re.sub(r'<javafx\.version>.*?</javafx\.version>', '<javafx.version>27</javafx.version>', content)
content = re.sub(r'<fxgl\.version>.*?</fxgl\.version>', '<fxgl.version>25.0.1</fxgl.version>', content)

if '<maven.compiler.release>' not in content:
    content = content.replace('<maven.compiler.target>27</maven.compiler.target>', '<maven.compiler.target>27</maven.compiler.target>\n        <maven.compiler.release>27</maven.compiler.release>')
if '<source.version>' not in content:
    content = content.replace('<fxgl.version>25.0.1</fxgl.version>', '<fxgl.version>25.0.1</fxgl.version>\n        <source.version>27</source.version>')

with open(root_pom, "w") as f: f.write(content)


# 2. Update Sub-Project POMs
for root, dirs, files in os.walk("."):
    if "target" in root: continue
    for file in files:
        if file == "pom.xml" and root != ".":
            path = os.path.join(root, file)
            with open(path, "r") as f: c = f.read()
            
            # Spring projects get java.version = 27
            if "50-02" in root or "50-03" in root:
                c = re.sub(r'<java\.version>.*?</java\.version>', '<java.version>27</java.version>', c)
            else:
                # Remove hardcoded compiler plugin configs
                c = re.sub(r'\s*<release>[^<]+</release>', '', c)
                c = re.sub(r'\s*<maven\.compiler\.source>[^<]+</maven\.compiler\.source>', '', c)
                c = re.sub(r'\s*<maven\.compiler\.target>[^<]+</maven\.compiler\.target>', '', c)
                # Be careful with <source> and <target> so we don't accidentally nuke non-compiler stuff if there are any
                # But looking at previous grep, they are inside <configuration> for compiler plugin
                c = re.sub(r'\s*<source>[^<]+</source>', '', c)
                c = re.sub(r'\s*<target>[^<]+</target>', '', c)
            
            # Fix hardcoded JavaFX versions
            if "01-11-JavaFX-Lab01" in root or "00-00-JavaFX-maven-archetype-generate/sample" in root:
                c = re.sub(r'<version>25\.0\.4</version>', '<version>${javafx.version}</version>', c)
                c = re.sub(r'<version>25</version>', '<version>${javafx.version}</version>', c)
            
            with open(path, "w") as f: f.write(c)

# 3. Non-Maven Projects
def replace_in_file(path, pattern, replacement):
    if os.path.exists(path):
        with open(path, "r") as f: c = f.read()
        c = re.sub(pattern, replacement, c)
        with open(path, "w") as f: f.write(c)

replace_in_file("01-01-JavaFX-HelloWorld-sbt/build.sbt", r'val javafxVersion = ".*?"', 'val javafxVersion = "27"')
replace_in_file("01-01-JavaFX-HelloWorld-Gradle/hellofx/build.gradle", r'version = ".*?"', 'version = "27"')
replace_in_file("01-01-JavaFX-HelloWorld-ant/build.xml", r'name="javafx\.version" value=".*?"', 'name="javafx.version" value="27"')
replace_in_file("01-01-JavaFX-HelloWorld-ant/build.xml", r'release="21"', 'release="27"')

# For Bazel, JavaFX artifact version is likely 27 instead of 21.0.4.
replace_in_file("01-01-JavaFX-HelloWorld-bazel/MODULE.bazel", r'21\.0\.4', '27')
replace_in_file("01-01-JavaFX-HelloWorld-bazel/BUILD.bazel", r'--release=21', '--release=27')

print("Update script complete.")
