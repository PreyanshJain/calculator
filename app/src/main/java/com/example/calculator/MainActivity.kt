package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        n1.setOnClickListener { appendOn("1",true) }
        n2.setOnClickListener { appendOn("2",true) }
        n3.setOnClickListener { appendOn("3",true) }
        n4.setOnClickListener { appendOn("4",true) }
        n5.setOnClickListener { appendOn("5",true) }
        n6.setOnClickListener { appendOn("6",true) }
        n7.setOnClickListener { appendOn("7",true) }
        n8.setOnClickListener { appendOn("8",true) }
        n9.setOnClickListener { appendOn("9",true) }
        n0.setOnClickListener { appendOn("0",true) }
        decimal.setOnClickListener { appendOn(".",true) }

        addition.setOnClickListener { appendOn("+",false) }
        subtract.setOnClickListener { appendOn("-",false) }
        multiply.setOnClickListener { appendOn("*",false) }
        divide.setOnClickListener { appendOn("/",false) }
        brace_open.setOnClickListener { appendOn("(",false) }
        brace_close.setOnClickListener { appendOn(")",false) }

        ac.setOnClickListener {
            var x = history.text.toString()
            var y = history2.text.toString()
            history.text = screen.text.toString() + " = " + result.text.toString()
            history2.text = x
            history3.text = y
            screen.text = ""
            result.text = ""
        }
        back.setOnClickListener {
            var string = screen.text.toString()
            if (string.isNotEmpty()){
                screen.text = string.substring(0,string.length-1)
            }
        }
        equals.setOnClickListener {
            try {
                val expression = ExpressionBuilder(screen.text.toString()).build()
                val answer = expression.evaluate()
                val longtext = answer.toLong()
                if (answer == longtext.toDouble()){
                    result.text = longtext.toString()
                }else{
                    result.text = answer.toString()
                }
            }catch (e:Exception){
                Log.d("Exception","message :" + e.message)
            }

        }


    }
    private fun appendOn(string: String, canClear:Boolean){
        if (result.text.isNotEmpty()){
            screen.text = ""
        }
        if (canClear){
            result.text = ""
            screen.append(string)
        }
        else{
            screen.append(result.text)
            screen.append(string)
            result.text = ""
        }

    }
}