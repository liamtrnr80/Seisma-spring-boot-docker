## Seisma Coding Exercise
A coding exercise for Seisma

## Instructions
To access the webapp click the following link:
http://ec2-18-218-92-19.us-east-2.compute.amazonaws.com:8080/ <br>

Select your valid json file and submit to be able to receive pay slip results

###A valid employee.json file (Replacing <> with actual values):
```
[
  {
    "firstName":"<Insert First Name>",
    "lastName":"<Insert Last Name>",
    "annualSalary":<Insert annual Salary>,
    "paymentMonth":<Insert month as int (e.g. 0 - 11>,
    "superRate":<Insert superRate>
  }
]

```

###An example employee.json file:
```
[
  {
    "firstName":"David",
    "lastName":"Rudd",
    "annualSalary":60050,
    "paymentMonth":1,
    "superRate":0.09
  },
  {
    "firstName":"Ryan",
    "lastName":"Chen",
    "annualSalary":120000,
    "paymentMonth":1,
    "superRate":0.1
  },
  {
    "firstName":"Yuno",
    "lastName":"Sykk",
    "annualSalary":600000,
    "paymentMonth":1,
    "superRate":0.2
  },
  {
    "firstName":"Irwin",
    "lastName":"Dundee",
    "annualSalary":80000,
    "paymentMonth":1,
    "superRate":0.1
  }
]

```
###A valid bracket.json object:
(Replacing <> with)
```
  {
    "fromBracket": <lower bracket>,
    "percent": <cent per dollar>,
    "lump": <lump sum>
  }
```


###A valid bracket.json file:
```
[
  {
    "fromBracket": 0,
    "percent": 0,
    "lump": 0
  },
  {
    "fromBracket": 18200,
    "percent": 19,
    "lump": 0
  },
  {
    "fromBracket": 37000,
    "percent": 32.5,
    "lump": 3572
  },
  {
    "fromBracket": 87000,
    "percent": 37,
    "lump": 19822
  },
  {
    "fromBracket": 180000,
    "percent": 45,
    "lump": 54232
  }
]
```

## Docker Quickstart
Run the web app with Docker by running the following command:
```
docker run -d -p 8080:8080 liamtrnr80/seisma-spring-boot-docker:latest
```
To change the external port the server uses, change the *first* instance of the port. For example, use port 8123:
```
docker run -d -p 8123:8080 liamtrnr80/seisma-spring-boot-docker:latest
```
**Building the Docker Image**
To build your own Docker Image, do the following:
1. Clone the repo
```
git clone https://github.com/liamtrnr80/Seisma-spring-boot-docker.git
cd CodingExercise
```
2. Run the Docker build command:
```
docker build -t liamtrnr80/seisma-spring-boot-docker:build .
```
