package the_null_pointer.secure_device.ui

sealed class NavItem(var screenRoute: String) {
    data object Splash : NavItem("splash")
    data object SearchScreen : NavItem("search-screen")
    data object Loading : NavItem("loading")
    data object Result: NavItem("result")
}