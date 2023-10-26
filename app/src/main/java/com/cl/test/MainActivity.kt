package com.cl.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.cl.log.*


class MainActivity : AppCompatActivity() {

    var viewPrinter: XViewPrinter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPrinter = XViewPrinter(this)
        findViewById<View>(R.id.btn_log).setOnClickListener {
            printLog()
//            XLog.i("当前保存log的日志路径：" + XFilePrinter.getInstance().logPath)
        }
        viewPrinter!!.viewProvider.showFloatingView()

    }

    private fun printLog() {
        XLogManager.getInstance().addPrinter(viewPrinter)
        XLog.log(object : XLogConfig() {
            override fun includeThread(): Boolean {
                return false
            }
        }, XLogType.E, "---", "5566")
        XLog.a("9900")
    }
}