package com.example.szshop.Screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.szshop.Navigation.NavigationItem
import com.example.szshop.R
import com.example.szshop.Screen.components.ProductItem
import com.example.szshop.Screen.components.ProductItem
import com.example.szshop.model.Product
import com.example.szshop.ui.theme.SZShopTheme

@Composable
fun ProductScreen (
    navController: NavController
) {
    var products= remember{
        getProductList()
    }
    LazyVerticalGrid(columns =GridCells.Fixed(2), modifier = Modifier.padding(8.dp) ) {
        items(products){
            ProductItem(product=it){
                navController.navigate("${NavigationItem.PRODUCT_DETAILS}/${it.id}")
            }
        }
        
    }
}
fun getProductList(): List<Product>{
    return listOf(
        Product(
            id = "1",
            color = Color.Black,
            price = 3000f,
            name = "spicel G ",
            discountPrice = 2500f,
            rating = 5.7f,
            imageRes = R.drawable.ssh1_png,
            size = 8

        ),
        Product(
            id = "2",
            color = Color.Green,
            price = 1800f,
            name = " runing Nike",
            discountPrice = 1500f,
            rating = 4.7f,
            imageRes = R.drawable.ssh2_png,
            size = 8

        ),
        Product(
            id = "3",
            color = Color.Red,
            price = 2000f,
            name = "sport nike",
            discountPrice = 1500f,
            rating = 4.7f,
            imageRes = R.drawable.sh3_png,
            size = 8

        ),
        Product(
            id = "4",
            color = Color.Magenta,
            price = 1600f,
            name = "Nike air max 97 OG",
            discountPrice = 1300f,
            rating = 4.7f,
            imageRes = R.drawable.sh_4png,
            size = 8

        ),
        Product(
            id = "5",
            color = Color.Green,
            price = 3699f,
            name = "Nike dunk",
            discountPrice = 2099f,
            rating = 4.7f,
            imageRes = R.drawable.sh5_png,
            size = 8

        ),
        Product(
            id = "6",
            color = Color.Blue,
            price = 1200f,
            name = "costom nike  ",
            discountPrice = 800f,
            rating = 4.7f,
            imageRes = R.drawable.ssh6_png,
            size = 8

        ),
        Product(
            id = "7",
            color = Color.Gray,
            price = 1299f,
            name = "Nike Air",
            discountPrice = 1070f,
            rating = 4.7f,
            imageRes = R.drawable.sh7_png,
            size = 8

        ),
        Product(
            id = "8",
            color = Color.Yellow,
            price = 4000f,
            name = "Jorden 4 ",
            discountPrice = 2599f,
            rating = 4.7f,
            imageRes = R.drawable.sh8_png,
            size = 8

        )

    )
}


