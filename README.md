# Anywherefitness Backend

<details>
<summary>GET https://anywherefitness704.herokuapp.com/users/users</summary>

```JSON
[
    {
        "userid": 4,
        "username": "admin",
        "primaryemail": "admin@lambdaschool.local",
        "useremails": [
            {
                "useremailid": 7,
                "useremail": "admin@email.local"
            },
            {
                "useremailid": 8,
                "useremail": "admin@mymail.local"
            }
        ],
        "roles": [
            {
                "role": {
                    "roleid": 1,
                    "name": "ADMIN"
                }
            },
            {
                "role": {
                    "roleid": 2,
                    "name": "USER"
                }
            },
            {
                "role": {
                    "roleid": 3,
                    "name": "DATA"
                }
            }
        ],
        "todos": [
            {
                "createdDate": "2020-06-25 11:34:03",
                "todoid": 5,
                "description": "Give Joe access rights",
                "completed": false
            },
            {
                "createdDate": "2020-06-25 11:34:03",
                "todoid": 6,
                "description": "Change the color of the home page",
                "completed": false
            }
        ]
    }
]
```
</details>


<details>
<summary>POST https://anywherefitness704.herokuapp.com/signup</summary>

Input:

```JSON
{
    "username" : "Mojo",
    "password" : "corgie",
    "primaryemail" : "home@local.house"
}
```

Return: 
```JSON
{
    "access_token": "35883fd1-cb99-4fd8-a1f1-dcba2bb0fae6",
    "token_type": "bearer",
    "scope": "read trust write"
}
```
</details>


<details>
<summary>POST https://anywherefitness704.herokuapp.com/login</summary>

```TEXT
Login using credentials from signup:
username: Mojo
password: corgie
token: 35883fd1-cb99-4fd8-a1f1-dcba2bb0fae6
```

```JSON
{
    "userid": 71,
    "username": "mojo",
    "primaryemail": "home@local.house",
    "useremails": [],
    "roles": [
        {
            "role": {
                "roleid": 2,
                "name": "USER"
            }
        }
    ],
    "todos": []
}
```
</details>


<details>
<summary>POST https://anywherefitness704.herokuapp.com/todos/user/71</summary>

```JSON
{
    "description": "Make coffee"
}
```

```JSON
{
    "userid": 71,
    "username": "mojo",
    "primaryemail": "home@local.house",
    "useremails": [],
    "roles": [
        {
            "role": {
                "roleid": 2,
                "name": "USER"
            }
        }
    ],
    "todos": [
        {
            "createdDate": "2020-06-25 11:43:26",
            "todoid": 72,
            "description": "Make coffee",
            "completed": false
        }
    ]
}
```
</details>

<details>
<summary>GET https://anywherefitness704.herokuapp.com/users/user/71</summary>

```JSON
{
    "userid": 71,
    "username": "mojo",
    "primaryemail": "home@local.house",
    "useremails": [],
    "roles": [
        {
            "role": {
                "roleid": 2,
                "name": "USER"
            }
        }
    ],
    "todos": [
        {
            "createdDate": "2020-06-25 11:43:26",
            "todoid": 72,
            "description": "Make coffee",
            "completed": false
        }
    ]
}
```
</details>

<details>
<summary>PATCH https://anywherefitness704.herokuapp.com/todos/todo/72 </summary>

```TEXT
No Body Data

Status OK
```

```JSON
{
    "userid": 71,
    "username": "mojo",
    "primaryemail": "home@local.house",
    "useremails": [],
    "roles": [
        {
            "role": {
                "roleid": 2,
                "name": "USER"
            }
        }
    ],
    "todos": [
        {
            "createdDate": "2020-06-25 11:43:26",
            "todoid": 72,
            "description": "Make coffee",
            "completed": true
        }
    ]
}
```
</details>


<details>
<summary>DELETE https://anywherefitness704.herokuapp.com/todos/todo/72</summary>

```TEXT
No Body Data

Status OK
```

```JSON
{
    "userid": 71,
    "username": "mojo",
    "primaryemail": "home@local.house",
    "useremails": [],
    "roles": [
        {
            "role": {
                "roleid": 2,
                "name": "USER"
            }
        }
    ],
    "todos": []
}
```
</details>
