package com.example.foodapp.db

import java.util.jar.Attributes

class MealsTypeConvertor {

    fun forAnyTOString (attribute: Any?):String{

        if (attribute == null)

            return " "
        return attribute as String

    }




    fun forStringTOString (attribute: String?):Any{

        if (attribute == null)

            return " "
        return attribute

    }


}