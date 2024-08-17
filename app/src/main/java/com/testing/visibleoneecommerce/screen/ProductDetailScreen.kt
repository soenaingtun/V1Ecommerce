package com.testing.visibleoneecommerce.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.testing.visibleoneecommerce.R
import com.testing.visibleoneecommerce.viewmodel.ProductDetailViewModel

@Composable
fun ProductDetailScreen(navController: NavHostController,
                        productId: String,
                        detailViewModel: ProductDetailViewModel = hiltViewModel()) {

    LaunchedEffect(Unit) {
        detailViewModel.getProductDetail(productId)
    }

    val state = detailViewModel.productDetailUiState.value

    // Using verticalScroll for scrolling
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .verticalScroll(rememberScrollState()) // Add scroll state here
            .padding(16.dp)
    ) {
        // Top navigation and image section

        state.data?.let { product ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .padding(16.dp)
            ) {
                // Product image
//                Image(
//                    painter = painterResource(id = R.drawable.nike), // Replace with your image resource
//                    contentDescription = "Nike Air Max 270",
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(16.dp)
//                )

                Image(
                    painter = rememberAsyncImagePainter(model = product.image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(180.dp)
                        .align(Alignment.Center).padding(10.dp),
                    contentScale = ContentScale.Fit
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                    ) {
                        IconButton(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier.align(Alignment.Center)
                        ) {
                            Icon(
                                Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.Black
                            )
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                    ) {
                        IconButton(
                            onClick = { /* Handle favorite click */ },
                            modifier = Modifier.align(Alignment.Center)
                        ) {
                            Icon(
                                Icons.Default.FavoriteBorder,
                                contentDescription = "Favorite",
                                tint = Color.Red
                            )
                        }
                    }
                }

            }

            Spacer(modifier = Modifier.height(16.dp))

            // Product details section

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Men's Shoes",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.weight(1f))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Star icon
                    Icon(
                        imageVector = Icons.Default.Star, // Replace with your star icon if different
                        contentDescription = "Rating",
                        tint = Color(0xFFf68027),
                        modifier = Modifier.size(28.dp)
                    )

                    // Spacer to add some space between the star icon and the text
                    Spacer(modifier = Modifier.width(4.dp))

                    // Text component
                    Text(
                        text = "(4.7)",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Gray
                    )
                }

            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Nike Air Max 270",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "$290.00",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }



            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Size selection
                Text(text = "Size:", style = MaterialTheme.typography.titleSmall)
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "US",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(6.dp)
                )

                Text(
                    text = "UK",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(6.dp)
                )

                Text(
                    text = "EU",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(6.dp)
                )

            }

            Spacer(modifier = Modifier.height(16.dp))
            SizeSelector()

            Spacer(modifier = Modifier.height(16.dp))

            ExpandableDescription(
                description = "This is a long description that can be expanded or collapsed. It provides detailed information about the item."
            )

            Divider()

            FreeDelivery(
                description = "Free Delivery"
            )

            Divider()

            Spacer(modifier = Modifier.height(8.dp))

            // Color selection
            ColorSelector()

            Spacer(modifier = Modifier.height(8.dp))

            // Quantity selector
            QuantitySelector()

            Spacer(modifier = Modifier.height(24.dp))

            // Add to Cart button
            Button(
                onClick = { /* Handle Add to Cart click */ },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFf68027)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Add to Cart",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}

@Composable
fun SizeSelector() {


    // List of size options
    val sizes = listOf("5", "5.5", "6", "6.5", "7","7.5")
// State to keep track of the selected size
    var selectedSize by remember { mutableStateOf<String?>(sizes.first()) }
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        sizes.forEach { size ->
            // Card for each size option
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .size(48.dp)
                    .clickable {
                        selectedSize = size
                        // Handle size selection
                    }
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            if (selectedSize == size) Color(0xFFf68027) else Color.White
                        )
                ) {
                    Text(text = size, color = Color.Black)
                }
            }
        }
    }
}


@Composable
fun ColorSelector() {


    // Example color options
    val colors = listOf(Color.Black, Color.Green, Color.Cyan, Color.Blue)
// State to keep track of the selected color, initialized to the first color
    var selectedColor by remember { mutableStateOf(colors.first()) }
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        colors.forEach { color ->
            // Card for each color option
            Card(
                shape = CircleShape,
                 modifier = Modifier
                     .size(60.dp)
                     .padding(8.dp)
                     .clickable {
                         selectedColor = color
                         // Handle color selection
                     }
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color, CircleShape) // Apply background color to Box
                ) {
                    // Show checkmark icon in the center if the color is selected
                    if (selectedColor == color) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Selected",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun QuantitySelector() {
    var quantity by remember { mutableStateOf(1) }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "Quantity", style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.weight(1f))

        // Row to arrange the buttons and the quantity text
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            // Minus button
            Card(
                shape = CircleShape,
                modifier = Modifier
                    .size(28.dp)
                    .clickable {
                        if (quantity > 1) quantity -= 1
                    }
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "-",
                        style = MaterialTheme.typography.titleMedium.copy(color = Color.Black),
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }

            // Quantity text
            Text(
                text = quantity.toString(),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            // Plus button
            Card(
                shape = CircleShape,
                modifier = Modifier
                    .size(28.dp)
                    .clickable {
                        quantity += 1
                    }
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "+",
                        style = MaterialTheme.typography.titleMedium.copy(color = Color.Black),
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun ExpandableDescription(description: String) {
    // State to keep track of whether the description is expanded or not
    var isExpanded by remember { mutableStateOf(false) }

    // Container for the expandable description
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        // Header with description and dropdown icon
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isExpanded = !isExpanded }
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Description",
                style = MaterialTheme.typography.bodyMedium
            )
            Icon(
                imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = if (isExpanded) "Collapse" else "Expand",
                tint = Color.Black
            )
        }

        // Description content, which expands or collapses
        AnimatedVisibility(visible = isExpanded) {
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}



@Composable
fun FreeDelivery(description: String) {
    // State to keep track of whether the description is expanded or not
    var isExpanded by remember { mutableStateOf(false) }

    // Container for the expandable description
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        // Header with description and dropdown icon
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isExpanded = !isExpanded }
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Free Delivery and Returns",
                style = MaterialTheme.typography.bodyMedium
            )
            Icon(
                imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = if (isExpanded) "Collapse" else "Expand",
                tint = Color.Black
            )
        }

        // Description content, which expands or collapses
        AnimatedVisibility(visible = isExpanded) {
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}
