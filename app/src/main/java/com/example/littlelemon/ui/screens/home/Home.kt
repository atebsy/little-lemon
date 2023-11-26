package com.example.littlelemon.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.littlelemon.Profile
import com.example.littlelemon.R
import com.example.littlelemon.database.MenuItem
import com.example.littlelemon.mocks.SampleMenuItemProvider
import com.example.littlelemon.ui.theme.LittleLemonColor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    menus: List<MenuItem>,
    navHostController: NavHostController?,
) {
    val menuItemState: MutableStateFlow<List<MenuItem>> =
        MutableStateFlow(menus)
    val items by menuItemState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
    )
    {
        var searchPhrase by remember {
            mutableStateOf("")
        }

        var category by remember {
            mutableStateOf("")
        }

        Row(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box {

            }
            Image(
                painter = painterResource(id = R.drawable.top_logo_image),
                contentDescription = "Logo with text",
                modifier = Modifier
                    .padding(top = 5.dp)
                    .width(100.dp)
                    .height(50.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.pp),
                contentDescription = "Logo with text",
                modifier = Modifier
                    .padding(top = 5.dp)
                    .width(50.dp)
                    .height(50.dp)
                    .clickable {
                        navHostController?.navigate(Profile.route)
                    }
            )

        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(LittleLemonColor.green)
                .padding(all = 10.dp)
        ) {
            Column {

                Text(
                    text = "Little Lemon",
                    fontFamily = FontFamily(Font(R.font.markazi_text_medium)),
                    fontSize = 55.sp,
                    letterSpacing = 1.sp,
                    color = LittleLemonColor.yellow,
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false,
                        ),
                    ),
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.padding(all = 0.dp)
                ) {
                    Column(modifier = Modifier.fillMaxWidth(0.65f)) {
                        Text(
                            text = "Chicago",
                            fontFamily = FontFamily(Font(R.font.markazi_text_medium)),
                            fontSize = 35.sp,
                            letterSpacing = 1.sp,
                            color = LittleLemonColor.cloud,
                            modifier = Modifier.padding(bottom = 10.dp, top = 0.dp)
                        )

                        Text(
                            text = stringResource(id = R.string.restaurant_desc),
                            fontFamily = FontFamily(Font(R.font.markazi_text_medium)),
                            fontSize = 20.sp,
                            letterSpacing = 0.sp,
                            color = LittleLemonColor.cloud
                        )
                    }

                    Image(
                        painterResource(R.drawable.home_photo),
                        contentDescription = "Home photo",
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .height(125.dp)
                    )
                }


                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search icon"
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Enter search phrase"
                        )
                    },
                    value = searchPhrase,
                    onValueChange = {
                        searchPhrase = it
                    },
                    shape = RoundedCornerShape(8.dp)
                )
            }
        }

        Text(
            text = "Order for delivery",
            fontFamily = FontFamily(Font(R.font.markazi_text_bold)),
            fontSize = 28.sp,
            letterSpacing = 0.sp,
            color = LittleLemonColor.charcoal,
            modifier = Modifier.padding(start = 10.dp, top = 40.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    category = "starters"
                },
                colors = ButtonDefaults.buttonColors(LittleLemonColor.highLight)
            ) {
                Text(
                    text = "Starters",
                    color = LittleLemonColor.green,
                    fontFamily = FontFamily(Font(R.font.markazi_text_bold)),
                    fontSize = 18.sp
                )
            }
            Button(
                onClick = {
                    category = "mains"
                },
                colors = ButtonDefaults.buttonColors(LittleLemonColor.highLight)
            ) {
                Text(
                    text = "Mains",
                    color = LittleLemonColor.green,
                    fontFamily = FontFamily(Font(R.font.markazi_text_bold)),
                    fontSize = 18.sp
                )
            }
            Button(
                onClick = {
                    category = "desserts"
                },
                colors = ButtonDefaults.buttonColors(LittleLemonColor.highLight)
            ) {
                Text(
                    text = "Desserts",
                    color = LittleLemonColor.green,
                    fontFamily = FontFamily(Font(R.font.markazi_text_bold)),
                    fontSize = 18.sp
                )
            }
            Button(
                onClick = {
                    category = "drinks"
                },
                colors = ButtonDefaults.buttonColors(LittleLemonColor.highLight)
            ) {
                Text(
                    text = "Drinks",
                    color = LittleLemonColor.green,
                    fontFamily = FontFamily(Font(R.font.markazi_text_bold)),
                    fontSize = 18.sp
                )
            }
        }
        if (searchPhrase.isNotEmpty()) {
            category = ""
            menuItemState.update {
                menus.filter { menu ->
                    menu.title.contains(searchPhrase, ignoreCase = true)
                }
            }
        }

        if (category.isNotEmpty()) {
            searchPhrase = ""
            menuItemState.update {
                menus.filter { menu ->
                    menu.category == category
                }
            }
        }



        MenuItemsList(items = items)
    }
}


@Composable
fun MenuItemsList(items: List<MenuItem>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 10.dp, vertical = 20.dp)
    ) {
        items(
            items = items,
            itemContent = {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.fillMaxWidth(0.75f)) {
                        Text(
                            text = it.title,
                            color = LittleLemonColor.green,
                            fontFamily = FontFamily(Font(R.font.markazi_text_bold)),
                            fontSize = 20.sp
                        )

                        Text(
                            text = it.description,
                            color = LittleLemonColor.green,
                            fontFamily = FontFamily(Font(R.font.markazi_text_regular)),
                            fontSize = 20.sp
                        )

                        Text(
                            text = "$${it.price}",
                            color = LittleLemonColor.green,
                            fontFamily = FontFamily(Font(R.font.markazi_text_regular)),
                            fontSize = 20.sp
                        )

                    }
                    Image(
                        painter = rememberAsyncImagePainter(it.image),
                        contentDescription = "${it.title} image",
                        modifier = Modifier.size(128.dp)
                    )
                }
                Divider(color = LittleLemonColor.highLight, thickness = 1.dp)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(
    @PreviewParameter(SampleMenuItemProvider::class)
    items: List<MenuItem>
) {
    HomeScreen(items, null)
}