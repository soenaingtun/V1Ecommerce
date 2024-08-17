package com.testing.visibleoneecommerce.screen


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.testing.visibleoneecommerce.R
import com.testing.visibleoneecommerce.model.ProductResponse
import com.testing.visibleoneecommerce.viewmodel.ProductListViewModel
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextOverflow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(navController: NavHostController,
                  productListViewModel: ProductListViewModel = hiltViewModel(),) {


    LaunchedEffect(Unit) {
        productListViewModel.getProductList()
    }
    val state = productListViewModel.productListUiState.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
    ) {
        TopAppBar(
            title = { },
            navigationIcon = {
                IconButton(onClick = { /* Handle menu click */ }) {
                    Icon(Icons.Default.Menu, contentDescription = "Menu")
                }
            },
            actions = {
                IconButton(onClick = { /* Handle cart click */ }) {
                    Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
                }
            },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = Color.Transparent,
                titleContentColor = Color.Black,
                actionIconContentColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth()
        )


        SearchBar()

        Spacer(modifier = Modifier.height(16.dp))

        BrandSelector()

        Spacer(modifier = Modifier.height(16.dp))
        state.isLoading.let {
            if (it) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // CircularProgressIndicator()
                    //CircularProgressIndicator(color = Color.Black)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Loading...")
                }
            }
        }

        state.error.let {

            if(it.isNotEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = it, color = Color.Red)
                }
            }

        }

        state.data.let {

            if (it != null) {
                ProductList(it, navController)
            }

        }

        Spacer(modifier = Modifier.height(16.dp))

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    TextField(
        value = "",
        onValueChange = { /* Handle search */ },
        placeholder = { Text("Search") },
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = null)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 0.dp, 8.dp, 0.dp)
            .height(48.dp),
        shape = RoundedCornerShape(24.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            focusedIndicatorColor = Color.Transparent, // Remove bottom line when focused
            unfocusedIndicatorColor = Color.Transparent // Remove bottom line when unfocused
        )
    )
}

@Composable
fun BrandSelector() {
    // List of brand images
    val brands = listOf(
        R.drawable.nike,
        R.drawable.adidas,
        R.drawable.puma,
        R.drawable.ic_underarmour,
        R.drawable.vans
    )

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp), // Spacing between items
        contentPadding = PaddingValues(horizontal = 16.dp) // Padding at the edges
    ) {
        items(brands) { brand ->
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .size(65.dp) // Set card size
                    .padding(2.dp) // Padding inside each card
                        ,
                        colors = CardDefaults.cardColors(
                        containerColor = Color.White // Set card background to white
                        )

            ) {
                Image(
                    painter = painterResource(id = brand),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}


@Composable
fun ProductList(products: List<ProductResponse>, // Replace with actual data type
                navController: NavHostController) {
    Column {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Popular",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.weight(1f))

            // Plus button
            Card(
                shape = CircleShape,
                modifier = Modifier
                    .size(38.dp)

            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = Icons.Filled.List, // Replace with your plus icon
                        contentDescription = "Plus",
                        tint = Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(products) { product ->
                ProductItem(
                    price = "$${product.price}",
                    name = product.title,
                    imageRes = product.image, // Replace with image loading logic
                    onClick = {
                        navController.navigate("productDetailScreen/${product.id}")
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }


    }
}

@Composable
fun ProductItem(
    price: String,
    name: String,
    imageRes: String, // This should be handled dynamically with image loading
    onClick: () -> Unit
) {

    // State to manage the animation
    var isVisible by remember { mutableStateOf(false) }

    // Trigger animation when the item is first displayed
    LaunchedEffect(Unit) {
        isVisible = true
    }

    // Animate the opacity and scale
    val alpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 500)
    )

    val scale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0.9f,
        animationSpec = tween(durationMillis = 500)
    )



    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick).graphicsLayer(
                scaleX = scale,
                scaleY = scale,
                alpha = alpha // Apply fade-in animation
            ),

        colors = CardDefaults.cardColors(
            containerColor = Color.White // Set card background to white
        )
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            // Product Image
            Image(
                painter = rememberAsyncImagePainter(model = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(140.dp)
                    .align(Alignment.Center)
                    .padding(10.dp),
                contentScale = ContentScale.Fit
            )

            // Price (Top Left)
            Text(
                text = price,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(12.dp)
            )

            // Favorite Icon (Top Right)
            IconButton(
                onClick = {},
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Favorite",
                    tint = Color(0xFFf68027)
                )
            }

            // Shopping Cart Icon (Bottom Right)
            IconButton(
                onClick = {},
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(12.dp)
                    .background(Color.Black, CircleShape)
                    .size(36.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Cart",
                    tint = Color.White
                )
            }

            // Product Name (Bottom Left)
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 12.dp, bottom = 12.dp, end = 80.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = name,
                    maxLines = 1,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomStart),
                    overflow = TextOverflow.Ellipsis // Show "..." if the text overflows
                )
            }
        }
    }
}
