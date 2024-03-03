package the_null_pointer.secure_device.ui.uitl

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry

object TransitionUtil {
    val AnimatedContentTransitionScope<NavBackStackEntry>.defaultEnterTransition: EnterTransition
        get() = slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Left,
            animationSpec = tween(700)
        )

    val AnimatedContentTransitionScope<NavBackStackEntry>.defaultExitTransition: ExitTransition
        get() = slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Left,
            animationSpec = tween(700)
        )

    val AnimatedContentTransitionScope<NavBackStackEntry>.defaultPopEnterTransition: EnterTransition
        get() = slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Right,
            animationSpec = tween(700)
        )

    val AnimatedContentTransitionScope<NavBackStackEntry>.defaultPopExitTransition: ExitTransition
        get() = slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Right,
            animationSpec = tween(700)
        )
}