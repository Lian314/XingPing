# 星萍

星萍 是一个使用 Jetpack Compose 构建的现代 Android 应用程序。该应用程序提供了用户登录、注册、星座信息查询、个人信息管理等功能，并且具有简洁美观的用户界面。

## 主要功能

### 登录和注册
- 用户可以通过登录和注册界面进行账户的创建和登录。

### 星座信息查询
- 用户可以选择星座并查看该星座的基本信息、星座类型及个性特征。

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
- 登录成功后，导航到“星⭐萍”页面，通过下拉菜单选择星座。选择星座后，会显示该星座的基本信息、类型及个性特征。

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

```plaintext
ConstellationApp
├── MainActivity.kt
│   └── MainScreen
│       └── NavHost
├── AuthScreens.kt
│   ├── LoginScreen
│   └── RegisterScreen
├── MoonScreen.kt
│   └── MoonScreen
├── StarScreen.kt
│   └── StarScreen
├── SunScreen.kt
│   └── SunScreen
├── ui
│   ├── theme
│   │   ├── Color.kt
│   │   ├── Theme.kt
│   │   └── Type.kt
├── res
│   ├── drawable
│   │   ├── ic_moon.xml
│   │   └── ic_sun.xml
│   ├── layout
│   │   └── activity_main.xml
├── AndroidManifest.xml
└── build.gradle.kts
```

## 开源许可

本项目基于 [MIT 许可证](LICENSE) 开源。
