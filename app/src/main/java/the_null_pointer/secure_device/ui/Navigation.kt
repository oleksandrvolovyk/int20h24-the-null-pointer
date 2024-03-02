package the_null_pointer.secure_device.ui

sealed class NavItem(var screenRoute: String) {
    data object SplashScreen : NavItem("splash-screen")
    data object SearchScreen : NavItem("search-screen")
    data object LoadingScreen : NavItem("loading-screen")
    data object ResultScreen: NavItem("result-screen")
}