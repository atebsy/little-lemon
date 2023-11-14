package com.example.littlelemon.mocks

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.littlelemon.MenuItem

class SampleMenuItemProvider() : PreviewParameterProvider<List<MenuItem>> {
    override val values: Sequence<List<MenuItem>> =
        sequenceOf(listOf(
            MenuItem(1,"Greek Salad", "The famous greek salad of crispy lettuce, peppers, olives, our Chicago.", 10.0,"https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/lemonDessert%202.jpg?raw=true","starters"),
            MenuItem(1,"Lemon Desert", "Traditional homemade Italian Lemon Ricotta Cake.", 10.0,"https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/grilledFish.jpg?raw=true","desserts"),
            MenuItem(1,"Grilled Fish", "Our Bruschetta is made from grilled bread that has been smeared with garlic and seasoned with salt and olive oil.", 10.0,"https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/pasta.jpg?raw=true","mains"),

        ))
}