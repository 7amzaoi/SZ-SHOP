package com.example.szshop.Screen

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.szshop.model.Product
import kotlinx.coroutines.delay


@Composable
fun ProductDetailsScreen(
    productId: String = "1",navController: NavController
) {
    var product = getProductList().find {
        it.id == productId
    }!!


    var xOffset by remember {
        mutableStateOf(800.dp)
    }
    var yOffset by remember {
        mutableStateOf(800.dp)
    }
    var selectedColor: Color by remember {
        mutableStateOf(product.color)
    }


    val animationXOffset = animateDpAsState(
        targetValue = xOffset, label = "", animationSpec = tween(
            durationMillis = 600, easing = FastOutLinearInEasing
        )
    )

    val animationYOffset = animateDpAsState(
        targetValue = yOffset, label = "", animationSpec = tween(
            durationMillis = 600, easing = FastOutLinearInEasing
        )
    )

    var productScale by remember {
        mutableStateOf(0.6f)
    }
    var productRotate by remember {
        mutableStateOf(-60f)
    }


    val animationProductScale = animateFloatAsState(targetValue = productScale, label = "")
    val animationProductRotate = animateFloatAsState(targetValue = productRotate, label = "")

    var selectedSize by remember {
        mutableStateOf(product.size.toString())
    }

    var isFavrate by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true) {
        delay(150)
        xOffset = 140.dp
        yOffset = (-130).dp
        productScale = 1f
        productRotate = -30f
    }



    ConstraintLayout(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        val (backButton, image, info, styleCircle) = createRefs()
        Box(
            modifier = Modifier
                .offset(x = animationXOffset.value, y = animationYOffset.value)
                .alpha(.3f)
                .size(400.dp)
                .background(color = selectedColor, shape = CircleShape)
                .constrainAs(styleCircle) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                },
        )
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .shadow(
                    elevation = 24.dp,
                    spotColor = DefaultShadowColor,
                    shape = RoundedCornerShape(12.dp)
                )
                .background(color = Color.White, shape = RoundedCornerShape(22.dp))
                .size(36.dp)
                .constrainAs(backButton) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
        ) {
            Icon(imageVector = Icons.Rounded.KeyboardArrowLeft, contentDescription = null)
        }

        Column(modifier = Modifier.constrainAs(info) {
            linkTo(parent.start, parent.end, startMargin = 16.dp)
            centerVerticallyTo(parent, bias = 0.25f)
        })  {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .scale(animationProductScale.value)
                    .rotate(animationProductRotate.value)
                    .padding(end = 48.dp, top = 30.dp)
                    .size(330.dp)
            )
            Column {

                Text(
                    text = "Sneakers",
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = product.name,
                    color = Color.Black,
                    fontSize = 22.sp,
                    modifier = Modifier.padding(top = 8.dp),
                    style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)),
                    textAlign = TextAlign.Center
                )
                Row(
                    modifier = Modifier.padding(2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = null,
                        tint = Color.Yellow
                    )

                    Text(
                        text = product.rating.toString(),
                        color = Color.Black,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 4.dp),
                        style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)),
                        textAlign = TextAlign.Center
                    )


                }

                Text(
                    text = "$.${product.discountPrice}",
                    modifier = Modifier.padding(top = 2.dp),
                    color = Color.Black,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Black,
                    style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
                )
                Text(
                    text = "size",
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Row(
                    modifier = Modifier
                        .wrapContentWidth()

                        .padding(top = 2.dp)
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    ProductSizeCard(size = "8", isSelected = selectedSize == "8") {
                        selectedSize = "8"

                    }
                    ProductSizeCard(size = "9", isSelected = selectedSize == "9") {
                        selectedSize = "9"

                    }
                    ProductSizeCard(size = "10", isSelected = selectedSize == "10") {
                        selectedSize = "10"

                    }
                    ProductSizeCard(size = "11", isSelected = selectedSize == "11") {
                        selectedSize = "11"

                    }
                    ProductSizeCard(size = "12", isSelected = selectedSize == "12") {
                        selectedSize = "12"

                    }
                    ProductSizeCard(size = "13", isSelected = selectedSize == "13") {
                        selectedSize = "13"

                    }
                    ProductSizeCard(size = "14", isSelected = selectedSize == "14") {
                        selectedSize = "14"

                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                }
                Text(
                    text = "Color",
                    modifier = Modifier.padding(top = 10.dp),
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 6.dp)
                        .padding(horizontal = 22.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    ProductColor(color = Color.Green, isSelected = selectedColor == Color.Green) {
                        selectedColor = Color.Green

                    }
                    ProductColor(color = Color.Blue, isSelected = selectedColor == Color.Blue) {
                        selectedColor = Color.Blue

                    }
                    ProductColor(color = Color.Red, isSelected = selectedColor == Color.Red) {
                        selectedColor = Color.Red

                    }
                    ProductColor(color = Color.Yellow, isSelected = selectedColor == Color.Yellow) {
                        selectedColor = Color.Yellow

                    }
                    ProductColor(color = Color.Cyan, isSelected = selectedColor == Color.Cyan) {
                        selectedColor = Color.Cyan

                    }

                }

                Text(
                    text = "nike, (stylized as NIKE) is an American athletic footwear and apparel corporation headquartered near Beaverton, Oregon, United States.[5] It is the world's largest supplier of athletic shoes and apparel and a major manufacturer of sports equipment, with revenue in excess of US $46 billion in its fiscal year 2022.",
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .padding(horizontal = 22.dp),
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
                )
                Spacer(modifier = Modifier.weight(1f))
                Row ( modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
                    .padding(horizontal = 22.dp)){


                    Button(onClick = { /*TODO*/ }, modifier = Modifier


                        .width(320.dp)
                        .height(48.dp)
                        .padding(start = 8.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(contentColor = Color.Yellow)
                    ) {
                        Icon(imageVector = Icons.Rounded.ShoppingCart , contentDescription =null )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = "Add to Cart", color = Color.White)

                    }


                    IconButton(onClick = { isFavrate=!isFavrate }) {
                        Icon(
                            imageVector = if (isFavrate) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                            contentDescription = null,
                            tint = if (isFavrate) Color.Red else MaterialTheme.colorScheme.onSurface)
                    }
                }





            }
        }


    }

}


@Composable
fun ProductSizeCard(
    modifier: Modifier = Modifier, size: String, isSelected: Boolean, onClick: () -> Unit
) {
    val backGroundColor = if (isSelected) {
        Color.Red
    } else {
        Color.White
    }
    val textColor = if (isSelected) Color.White else Color.Black
    Box(
        modifier = Modifier
            .size(72.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(backGroundColor)
            .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(15.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = size,
            color = textColor,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ProductColor(
    modifier: Modifier = Modifier,
    color: Color,
    isSelected: Boolean, onClick: () -> Unit
) {
    val boarderColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
    Box(
        modifier = modifier
            .border(width = 0.5.dp, shape = CircleShape, color = boarderColor)
            .padding(4.dp)
            .background(color, shape = CircleShape)
            .size(24.dp)
            .clip(CircleShape)
            .clickable {
                onClick()
            }
    )

}
