package com.example.phnapplication.models

data class Users(val id: Int,
                 val name: String,
                 val username: String,
                 val email: String,
                 val phone: String,
                 val company: Company)

data class Company(val name: String,
                   val catchPhrase: String,
                   val bs: String)
