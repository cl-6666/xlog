# XLog通用安卓框架  

### 图片演示  
<img src="https://github.com/cl-6666/xlog/blob/master/img.png" width="300" height="500" alt="演示"/><br/>  


版本更新历史：  
[![](https://jitpack.io/v/cl-6666/xlog.svg)](https://jitpack.io/#cl-6666/xlog) 

V3.0.0：   
1.初始化参数配置优化
2.本地日志存储本地优化

V2.0.0：   
修复堆栈信息显示问题

V1.0.0：    
1.能够打印堆栈信息  
2.支持任何数据类型的打印  
3.能够实现日志可视化   
4.能够实现文件打印模块   
5.支持不同打印器的插拔  



### 库引用  
```
  implementation 'com.github.cl-6666:xlog:v3.0.0'
```  
```
jdk11依赖方式
需要在settings.gradle设置
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter() // Warning: this repository is going to shut down soon
        maven { url 'https://jitpack.io' }
    }
}
```
### 初始化介绍  
```java
在Application里面初始化
        //初始化日志框架
        XLogConfig logConfig = new XLogConfig.Builder()
                //全局TAG
                .setGlobalTag("TAG")
                //是否包含线程信息
                .setWhetherThread(true)
                //Xlog是否可用
                .setWhetherToPrint(true)
                //是否存储日志到本地  log文件的有效时长，单位毫秒，<=0表示一直有效
                .setStoreLog(true,0)
                //堆栈的深度
                .setStackDeep(5)
                .setInjectSequence(new XLogConfig.JsonParser() {
                    @Override
                    public String toJson(Object src) {
                        String json = new Gson().toJson(src);
                        return json;
                    }
                }).build();

        XLogManager.getInstance().init(logConfig, new XConsolePrinter());

```

### 使用介绍  
```java 
class MainActivity : AppCompatActivity() {

    var viewPrinter: XViewPrinter? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPrinter = XViewPrinter(this)
        findViewById<View>(R.id.btn_log).setOnClickListener {
            printLog()
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
```
### 日志保存截图
```java
假如想把日志文件上传到服务器，可以直接传入路径即可，框架没有做上传相关逻辑
XLog.i("当前保存log的日志路径：" + XFilePrinter.getInstance().logPath)
```
<img src="https://github.com/cl-6666/xlog/blob/master/imglog.png" width="700" height="500" alt="演示"/><br/>  
