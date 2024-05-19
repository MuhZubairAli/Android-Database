# Database Module for Android
This is the Simplest ORM for Android which has support for API >= 17. It supports automatic Local Database creation from the Models list and has the utility to generate TSQL scripts for databases from given models. Currently, it supports CRUD operations for the Database.

It makes use of the following Annotations
- PrimaryKey (autogenerate = false): for local database
- SqlPrimaryKey (autogenerate = false, seed = 1, increment = 1) : for TSQL script
- UniqueKey (index = default): both local database and TSQL script
- SqlExclude: for TSQL
- Default(value = ""): for both local database and TSQL
- SqlDataType(value = ""): for TSQL special datatypes
- NotNull: for both local database and TSQL
- Table(name = "", version = 1): for both local database and TSQL
- SerializedName(value="") for both local database and TSQL (This annotation is from GSON library for setting different name)

### Support for ModelBasedRepository
This module also supports ModelBasedRepository for Database with inbuilt ExecutorService for computationally expensive database operations and all necessary convenience methods for CRUD operations.

### Note
Multiple unique keys can be applied with different indexes. TSQL query for composite Primary Key works as expected with default ASC order for each column. But SQLite gives errors on the composite primary key.


## Installation
In order to use this library in you android application follow following steps

### Step 1: Get personal access token from github with read:packages scope *(this could be skipped as I provided my Token and Username for testing)*

  1. Log in to your GitHub account and goto **Settings** (click on profile photo at top right corner and from the menu click *Settings*)

  ![image](https://github.com/MuhZubairAli/Android-Utilities/assets/22114590/efc2924e-7537-4650-8328-482cb9fd64b3)

  3. In the left sidebar, click  **Developer settings**.
  4. In the left sidebar, click **Personal Access Tokens**.
  5. Click Generate new token.
  6. In the "Note" field, give your token a descriptive name like *"Token for reading github packages"*.
  7. To give your token an expiration, setting it to *No expiration* is fine because we will use it to download packages.
  8. Select the scope to *read:packages* as shown in below picture
  
  ![image](https://github.com/MuhZubairAli/Android-Utilities/assets/22114590/e70780c3-c26c-4846-af63-be2632c607e1)

  9. Click Generate token.

### Step 2: Add maven repository for dependency downloads
Depending on your gradle version add repository for all projects, from gradle 7.3.3 add a repository as follows
add maven repository in *settings.gradle* file

```
pluginManagement{
    //...
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        //...
        maven {
            url uri('https://maven.pkg.github.com/MuhZubairAli/*')
            credentials {
                username 'YourGithubUsername'
                password 'PersonalAccessToken'
            }
        }
    }
}
```

*If you are facing any issues in generating token you use below username and token*

```
maven {
    url uri('https://maven.pkg.github.com/MuhZubairAli/*')
    credentials {
        username 'MuhZubairAli'
        password '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'
    }
}
```

### Step 3: Add dependencies into build.gradle file
If you want to use libray in your application add dependency in your ROOT_DIRECTORY/app/build.gradle or if you intend to use it in any module then add it to ROOT_DIRECTORY/MODULE_DIRECTORY/build.gradle

```
dependencies {
  implementation 'com.github.muhzubairali:database:$VERSION'
  // other dependencies
}
```
To get the latest version click package, the latest package details could be found in POM (or in the title)


![image](https://github.com/MuhZubairAli/Android-Utilities/assets/22114590/5282a33f-4c10-405d-b737-ddd5fc946c08)

## Todo
- [x] Add support for a custom query select statement by returning a List of HashMap(s) of resulting columns
- [x] Automatic consolidated version calculation for Database
- [ ] Implement support for custom name property in Table annotation (table name in createTable, CRUD methods, etc)
- [ ] Implement support for SerializedName(value="") for both local database and TSQL (This annotation is from GSON library for setting different name for field)
