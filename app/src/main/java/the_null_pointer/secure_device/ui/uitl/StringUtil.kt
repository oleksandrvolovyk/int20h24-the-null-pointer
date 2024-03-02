package the_null_pointer.secure_device.ui.uitl

import java.util.Locale

object StringUtil {
    fun String.capitalize(): String =
        this.replaceFirstChar { char ->
            if (char.isLowerCase()) char.titlecase(
                Locale.ROOT
            ) else this
        }
}