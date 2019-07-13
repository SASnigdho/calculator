package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Number
        one_tv_Id.setOnClickListener { appendOnExpression("1", true) }
        two_tv_Id.setOnClickListener { appendOnExpression("2", true) }
        three_tv_Id.setOnClickListener { appendOnExpression("3", true) }
        four_tv_Id.setOnClickListener { appendOnExpression("4", true) }
        five_tv_Id.setOnClickListener { appendOnExpression("5", true) }
        six_tv_Id.setOnClickListener { appendOnExpression("6", true) }
        seven_tv_Id.setOnClickListener { appendOnExpression("7", true) }
        eight_tv_Id.setOnClickListener { appendOnExpression("8", true) }
        nine_tv_Id.setOnClickListener { appendOnExpression("9", true) }
        zero_tv_Id.setOnClickListener { appendOnExpression("0", true) }
        dot_tv_Id.setOnClickListener { appendOnExpression(".", true) }

        //Operatior
        plus_tv_Id.setOnClickListener { appendOnExpression("+", false) }
        minus_tv_Id.setOnClickListener { appendOnExpression("-", false) }
        divide_tv_Id.setOnClickListener { appendOnExpression("/", false) }
        mul_tv_Id.setOnClickListener { appendOnExpression("*", false) }
        openParentheses_tv_Id.setOnClickListener { appendOnExpression("(", false) }
        closeParentheses_tv_Id.setOnClickListener { appendOnExpression(")", false) }

        allClear_tv_Id.setOnClickListener {
            expression_tv_Id.text = ""
            result_tv_Id.text = ""
        }

        back_tv_Id.setOnClickListener {
            var string = expression_tv_Id.text.toString()

            if (string.isNotEmpty())
            {
                expression_tv_Id.text = string.substring(0, string.length - 1)
            }
            result_tv_Id.text = ""
        }

        equal_tv_Id.setOnClickListener{
            try {
                val expression = ExpressionBuilder(expression_tv_Id.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()

                if(result == longResult.toDouble())
                {
                    result_tv_Id.text = longResult.toString()
                } else {
                    result_tv_Id.text = result.toString()
                }

            } catch (e: Exception)
            {
                Toast.makeText(this, "Message: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun appendOnExpression(inputString: String, canClear: Boolean)
    {
        if (result_tv_Id.text.isNotEmpty())
        {
            expression_tv_Id.text = ""
        }

        if (canClear)
        {
            result_tv_Id.text = ""
            expression_tv_Id.append(inputString)
        } else
        {
            expression_tv_Id.append(result_tv_Id.text)
            expression_tv_Id.append(inputString)
            result_tv_Id.text = ""
        }
    }
}
