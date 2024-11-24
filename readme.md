# Spring-Boot3 openAPI 

In API first design, create API first and than generate the generate the stub code by using openAPI generator.

## Useful link
#### OpenAPI Specification
    https://swagger.io/specification/
#### Basic Structure
    https://swagger.io/docs/specification/v3_0/basic-structure/
#### Example
    https://www.baeldung.com/java-openapi-generator-server
    https://mydeveloperplanet.com/2022/02/08/generate-server-code-using-openapi-generator/
    https://www.youtube.com/watch?v=7oujW3sk5yg
    https://www.youtube.com/watch?v=Ih7gIHSbVtM&t=30s

#### Swagger Editor to create API
    https://editor.swagger.io/

## OpenAPI specification
#### API metadata
```
 openapi: "3.0.1"

info:
  title: "User"
  version: "1"
  description: User API

servers:
  - url: http://localhost:8080/api
    description: Local server
```
#### paths section
Api class will create with path name: 'UserApi.java' and operationId is method name
```
paths:

  /user:
    post:
      summary: Save User
      operationId: saveUser
      requestBody:
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/UserRequestDTO'
      responses:
        200:
          description: OK
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/UserResponseDTO'

  /user/{id}:
    get:
      summary: Get User By Id
      operationId: getUserById
      parameters:
        - name: id
          in: path
          description: Unique Id of an User
          required: true
          schema:
            type: string
            format: uuid
      responses:
        200:
          description: OK
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/UserResponseDTO'
```

#### components section
  used schemas are defined with request and response
```
components:
  schemas:
    UserRequestDTO:
      type: object
      description: Request Data element for User
      required:
        - email
      properties:
        id:
          type: string
          format: uuid
        email:
          type: string
        firstName:
          type: string
          minLength: 3
        lastName:
          type: string

    UserResponseDTO:
      type: object
      description: Response Data element for User
      required:
        - email
      properties:
        id:
          type: string
          format: uuid
        email:
          type: string
        firstName:
          type: string
          minLength: 3
        lastName:
          type: string
```
#### components section
  used schemas are defined with Error 
  ```
componenet
  schema
    Error:
      type: object
      properties:
        code:
          type: integer
          format: int32
        message:
          type: string
```

## pom..xml
#### dependency
```
<!-- spring -->
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-validation</artifactId>
  </dependency>
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
  </dependency>

<!-- swagger -->
    <dependency>
       <groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
			<version>2.5.0</version>
		</dependency>

		<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-ui</artifactId>
			<version>1.8.0</version>
		</dependency>

		<dependency>
			<groupId>org.openapitools</groupId>
			<artifactId>jackson-databind-nullable</artifactId>
			<version>0.2.6</version>
		</dependency>

		<dependency>
			<groupId>io.swagger</groupId>
			<artifactId>swagger-annotations</artifactId>
			<version>1.6.8</version>
		</dependency>
```
#### maven plugin
```
 <plugin>
				<groupId>org.openapitools</groupId>
				<artifactId>openapi-generator-maven-plugin</artifactId>
				<version>7.10.0</version>
				<executions>
					<execution>
						<goals>
							<goal>generate</goal>
						</goals>
						<configuration>
							<inputSpec>
								${project.basedir}/src/main/resources/swagger/api.yml
							</inputSpec>
							<generatorName>spring</generatorName>
							<apiPackage>com.springboot.openapi.generator.api</apiPackage>
							<modelPackage>com.springboot.openapi.generator.dto</modelPackage>
							<supportingFilesToGenerate>
								ApiUtil.java
							</supportingFilesToGenerate>
							<configOptions>
								<delegatePattern>true</delegatePattern>
								<serializableModel>true</serializableModel>
								<useJakartaEe>true</useJakartaEe>
							</configOptions>
						</configuration>
					</execution>
				</executions>
			</plugin>
```

