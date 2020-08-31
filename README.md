# XLog通用安卓框架  

### 图片演示  
<img src="https://github.com/cl-6666/xlog/blob/master/img.png" width="200" height="200" alt="演示"/><br/>  

### 库引用  
```
implementation 'com.github.cl-6666:xlog:v1.0.0'
```  
### 使用介绍  
```java
kotlin初始化案例  
    class MApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        HiLogManager.init(
            object : HiLogConfig() {
                override fun injectJsonParser(): JsonParser? {
                    return JsonParser { src -> Gson().toJson(src) }
                }

                override fun getGlobalTag(): String {
                    return "MApplication"
                }

                override fun enable(): Boolean {
                    return true
                }

                override fun includeThread(): Boolean {
                    return true
                }

                override fun stackTraceDepth(): Int {
                    return 5
                }
            },
            HiConsolePrinter(),
            HiFilePrinter.getInstance(applicationContext.cacheDir.absolutePath, 0)
        )
    }
}  
    
    
Java初始化案例  
  public class MApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        XLogManager.init(new XLogConfig(){
            @Override
            public String getGlobalTag() {
                return "MApplication";
            }

            @Override
            public boolean enable() {
                return true;
            }

            @Override
            public JsonParser injectJsonParser() {
                //TODO 根据需求自行添加
                return super.injectJsonParser();
            }

            @Override
            public boolean includeThread() {
                return true;
            }

            @Override
            public int stackTraceDepth() {
                return 5;
            }
        },new XConsolePrinter(),XFilePrinter.getInstance(getApplicationContext().getCacheDir().getAbsolutePath(),0));
    }
}

界面日志显示演示：  
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
