# 星萍

星萍 是一个使用 Jetpack Compose 构建的现代 Android 应用程序。该应用程序提供了用户登录、注册、星座信息查询、个人信息管理等功能，并且具有简洁美观的用户界面。

## 主要功能

### 登录和注册
- 用户可以通过登录和注册界面进行账户的创建和登录。

### 星座信息查询
- 用户可以选择星座并查看该星座的基本信息、个性特征以及联网导航至星座屋查看今日运势。

### 个人信息管理
- 用户可以在“个人信息”界面填写和查看自己的基本信息，包括昵称、生日、性别、MBTI类型等。

### 其他功能
- 应用提供时钟、日历和随机祝福语显示功能，增加用户体验的趣味性。

## 安装步骤

1. 克隆仓库到本地：
    ```bash
    git clone https://github.com/yourusername/ConstellationApp.git
    cd ConstellationApp
    ```

2. 打开 Android Studio，选择“Open an existing project”，然后选择克隆的项目目录。

3. 确保你已经安装了所需的 SDK 和库，然后点击“Sync Project with Gradle Files”。

4. 连接你的 Android 设备或启动模拟器，然后点击“Run”按钮运行应用程序。

## 使用方法

### 登录和注册
- 启动应用后，你会看到登录界面。输入用户名和密码，点击“登录”按钮。如果没有账号，可以点击“注册”链接跳转到注册界面。

### 星座信息查询
- 登录成功后，导航到“星⭐萍”页面，通过下拉菜单选择星座。选择星座后，会显示该星座的基本信息、个性特征和联网导航至星座屋查看今日运势。

### 个人信息管理
- 导航到“个人信息”页面，填写个人信息表单，包括昵称、生日、性别、MBTI类型、职业、微信号和QQ号。填写完成后，信息会自动保存到本地。

### 时钟和日历
- 导航到“人生几何”页面，可以查看当前时间和日期，并点击按钮获取随机祝福语。

## 技术亮点

### 使用Jetpack Compose构建UI
- 应用使用Jetpack Compose构建现代化的响应式UI，简化了UI开发过程。通过@Composable函数定义UI组件，使用MaterialTheme进行主题管理。

### 导航组件实现页面切换
- 使用Jetpack Navigation组件实现页面之间的无缝切换，提升用户体验。通过NavController和NavHost定义导航图，使用composable函数定义各个页面。

### 数据持久化
- 应用程序使用SharedPreferences保存用户的个人信息，实现数据的持久化。通过SharedPreferences API读取和写入用户数据。

## 软件架构图

![架构](https://github.com/user-attachments/assets/6389b38b-c277-4d41-909f-0d4490cd4710)

```plaintext
ConstellationApp
├── .gradle                        
├── .idea                          
├── .kotlin                        
├── app                           // 应用程序模块
│   ├── build                     // 编译输出文件夹
│   ├── src                       // 源代码目录
│   │   ├── androidTest           // Android 测试代码目录
│   │   ├── main                  // 主目录
│   │   │    └── java             // Java 源代码目录
│   │   │         └── com.example.constellationapp // 应用程序包名目录
│   │   │               ├── ui.theme                // UI 主题相关代码
│   │   │               │   ├── Color.kt            // 颜色定义文件
│   │   │               │   ├── Theme.kt            // 主题定义文件
│   │   │               │   └── Type.kt             // 字体样式定义文件
│   │   │               ├── MoonScreen.kt           // 月亮屏幕代码（时间页面）
│   │   │               ├── SunScreen.kt            // 太阳屏幕代码（个人信息页面）
│   │   │               ├── AuthScreens.kt          // 认证屏幕代码（登陆注册页面）
│   │   │               ├── MainActivity.kt         // 主活动代码
│   │   │               └── StarScreen.kt           // 星星屏幕代码（星座页面）
│   │   └── res                         // 资源文件目录
│   │       ├── drawable                // 可绘制资源目录
│   │       ├── drawable-v24            // 适用于 API 24 及以上的可绘制资源
│   │       ├── layout                  // 布局文件目录
│   │       ├── mipmap-anydpi-v26       // 适用于所有密度的启动图标资源（API 26 及以上）
│   │       ├── mipmap-hdpi             // 高密度启动图标资源
│   │       ├── mipmap-mdpi             // 中等密度启动图标资源
│   │       ├── mipmap-xhdpi            // 超高密度启动图标资源
│   │       ├── mipmap-xxhdpi           // 超超高密度启动图标资源
│   │       ├── mipmap-xxxhdpi          // 超超超高密度启动图标资源
│   │       ├── raw                     // 原始资源目录
│   │       ├── values                  // 值资源目录（字符串、颜色、样式等）
│   │       └── xml                     // XML 配置文件目录
│   │           └── AndroidManifest.xml // Android 清单文件
│   └── test [unitTest]            // 单元测试目录
│       ├── .gitignore              
│       ├── build.gradle.kts       
│       └── proguard-rules.pro      
├── gradle                         
│   ├── .gitignore                
│   ├── build.gradle.kts           // Gradle 构建脚本（Kotlin DSL）
│   ├── gradle.properties          // Gradle 属性文件
│   ├── gradlew                   
│   ├── gradlew.bat                
│   ├── local.properties            // 本地属性配置文件
│   └── settings.gradle.kts         // Gradle 设置文件（Kotlin DSL）
└── External Libraries              // 外部库
    └── Scratches and Consoles      // 临时文件和控制台
```

