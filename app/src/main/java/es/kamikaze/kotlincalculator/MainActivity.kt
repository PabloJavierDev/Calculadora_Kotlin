package es.kamikaze.kotlincalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var firstNumber = 0.0
    private var seccondNumber = 0.0
    private var operation: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        operation = null

        bt0.setOnClickListener(this)
        bt1.setOnClickListener(this)
        bt2.setOnClickListener(this)
        bt3.setOnClickListener(this)
        bt4.setOnClickListener(this)
        bt5.setOnClickListener(this)
        bt6.setOnClickListener(this)
        bt7.setOnClickListener(this)
        bt8.setOnClickListener(this)
        bt9.setOnClickListener(this)
        btClear.setOnClickListener(this)
        btComma.setOnClickListener(this)
        btDiv.setOnClickListener(this)
        btPlus.setOnClickListener(this)
        btMul.setOnClickListener(this)
        btMinus.setOnClickListener(this)
        btEqual.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            bt0 -> onNumberPressed("0")
            bt1 -> onNumberPressed("1")
            bt2 -> onNumberPressed("2")
            bt3 -> onNumberPressed("3")
            bt4 -> onNumberPressed("4")
            bt5 -> onNumberPressed("5")
            bt6 -> onNumberPressed("6")
            bt7 -> onNumberPressed("7")
            bt8 -> onNumberPressed("8")
            bt9 -> onNumberPressed("9")
            btComma -> onNumberPressed(".")

            btDiv -> onOperationPressed("/")
            btPlus -> onOperationPressed("+")
            btMul -> onOperationPressed("x")
            btMinus -> onOperationPressed("-")

            btClear -> onClearPressed()
            btEqual -> onEqualPressed()
        }
    }

    private fun onEqualPressed() {
        seccondNumber = screen.text.toString().toDouble()

        var result: Double? = when(operation){
            "+" -> firstNumber + seccondNumber
            "-" -> firstNumber - seccondNumber
            "x" -> firstNumber * seccondNumber
            "/" -> firstNumber / seccondNumber
            else -> null
        }

        if (result != null) {
            operation = null
            firstNumber = result.toString().toDouble()

            screen.text = ""

            try {
                onNumberPressed(if (result.toString().endsWith(".0")) {
                    result.toString().replace(".0", "")
                } else {
                    "%.6f".format(result)
                })
            }catch (e:java.lang.Exception){
                e.printStackTrace()
            }
        }
    }

    private fun onClearPressed() {
        firstNumber = 0.0
        seccondNumber = 0.0
        operation = null
        screen.text = "0"
    }

    private fun onNumberPressed(number : String){
        if (!(number.contains(".") && screen.text.toString().contains("."))){
            renderScreen(number)
            checkOperation()
        }
    }

    private fun onOperationPressed(operation : String){
        this.operation = operation
        firstNumber = screen.text.toString().toDouble()

        screen.text = "0"
    }

    private fun renderScreen(number: String){
        val result = if (screen.text == "0" && number != ".") {
            number
        } else {
            "${screen.text}${number}"
        }

        screen.text = result
    }


    private fun checkOperation() {
        if (operation == null) {
            firstNumber = screen.text.toString().toDouble()
            seccondNumber = 0.0
        }else {
            seccondNumber = screen.text.toString().toDouble()
        }
    }


}