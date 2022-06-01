# Java template project

This is a sample Java project build with maven and Junit as an external maven dependency for testing purposes.

## About this template

Hi, I created this template to help you get started with a new project in the WESCO organisation

Some decisions I have made while creating this template are:

- Create a project structure that is as modular as possible.
- Keep it simple and easy to maintain.
- Allow for a lot of flexibility and customizability.
- Low dependency

Note: After creating the repo please use the use `mvn clean install` to build the package.

## Structure

Lets take a look at the structure of this template:

```text
├── Dockerfile                      # The file to build a container using buildah or docker
├── docs                            # Documentation site (add more .md files here)
│   └── index.md                    # The index page for the docs site we are using mkdocs visit [mkdocs.org](https://www.mkdocs.org)
├── .github                         # Github metadata for repository
│   ├── PULL_REQUEST_TEMPLATE.md    # Pull request template going to be used while raising a request
│   └── workflows                   # The CI pipeline for Github Actions
├── .gitignore                      # A list of files to ignore when pushing to Github
├── CHANGELOG.md                    # Auto generated list of changes to the project
├── mkdocs.yml                      # Configuration for documentation site
├── src                             # The main java package for the project
│   ├── main                        # Main source of the code lives over here
        ├── java                    # Main entry point for java classes

│       ├── resources
│           └── log4j.yml           # Log4j configuration
│   ├── test
        ├── java                    # Main entry point for all java test classes
            └── SampleTest.java
│       ├── resources
│           └──log4.test.yml        # Log4j test configuration
├── README.md                       # The main readme for the project
```

## FAQs

Frequent asked questions.

### Why this template been build with java 11 ?

To decide on the version of the project which is not fixed you can update the version of java on pom.xml file an docker file to leverage any java version to add, please add dev secops team on review, if doing so.

### Why the `pom.xml` is not having other dependencies ?

This template is a low dependency project, so it doesn't have any extra dependencies.
You can add your dependency from maven public artifact for now, future rollout will have private repository as well.

### Why to include `tests`, `CHANGELOG` and `Dockerfile` as part of the release?

The `Dockerfile` file is used to create docker image which will be later on used to deploy into our kubernetes clustor.

The `CHANGELOG` file is used to create a release/change log history template, please follow the [https://keepachangelog.com/en/1.0.0/](guidelines)

The `tests` are for adding any test case which are been tested by pytest utility.


### Why this template is using [pre-commit](https://pre-commit.com/) ?

pre-commit is an excellent tool to automate checks and formatting on your code. pre-commit been added already.
To make it work please follow the steps below.

### Is there a Linting and auto formatter in place ?

Yes, I have set up a linter and formatter with help of tools such as pre-commit, The same can be activated from below.

### How to install pre-commit?

To enable pre-commit please follow these steps.

- Step 1: install python
- Step 2: run `pip3 install pre-commit`
- Step 3: run `pre-commit install`

Once this is done pre-commit hooks will be added and linting will automatically take place on every commit.

## The Dockerfile

This project can be build and run through docker file.

```bash
❯ docker
Usage: docker <target>

Targets:
help:                                            ## Show the help.
build -t <tag_name> .:                           ## Build the Dockerfile
run -p 9090:9090 -v /<dir>:/data <tag_name>      ## Runs the docker image on port 9090 and host localhost
```

Guideline to follow:

- Unit Test: <https://anixter.atlassian.net/wiki/spaces/DIGIT/pages/2923757598/Guidelines+for+Unit+test>
- Wesco Policies: <https://anixter.atlassian.net/wiki/spaces/DSO/pages/2870640838/Policies>
