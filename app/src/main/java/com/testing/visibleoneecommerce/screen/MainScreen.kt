package com.testing.visibleoneecommerce.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

data class TabBarItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeAmount: Int? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    // Setting up the individual tabs
    val homeTab = TabBarItem(title = "Home", selectedIcon = Icons.Filled.Home, unselectedIcon = Icons.Outlined.Home)
    val cartTab = TabBarItem(title = "Carts", selectedIcon = Icons.Filled.ShoppingCart, unselectedIcon = Icons.Outlined.ShoppingCart, badgeAmount = 3)
    val saveTab = TabBarItem(title = "Save", selectedIcon = Icons.Filled.Favorite, unselectedIcon = Icons.Outlined.Favorite)
    val searchTab = TabBarItem(title = "Search", selectedIcon = Icons.Filled.Search, unselectedIcon = Icons.Outlined.Search)

    // Creating a list of all the tabs
    val tabBarItems = listOf(homeTab, cartTab, saveTab, searchTab)

//    VisibleOneEcommerceTheme {
//        // A surface container using the 'background' color from the theme
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//            color = MaterialTheme.colorScheme.background
//        ) {
            Scaffold(bottomBar = { TabView(tabBarItems, navController) }) {

                NavHost(
                    navController = navController,
                    modifier = Modifier.padding(it),
                    startDestination = homeTab.title
                ) {
                    composable(homeTab.title) { 
                        ProductScreen(navController = navController)
                    }
                    composable(cartTab.title) {
                        CartScreen(navController = navController)
                    }
                    composable(saveTab.title) {
                        SaveScreen(navController = navController)
                    }
                    composable(searchTab.title) {
                        SearchScreen(navController = navController)
                    }
                    composable("productDetailScreen/{id}") {
                        val productId = it.arguments?.getString("id")
                        if (productId != null) {
                            ProductDetailScreen(navController,productId)
                        }
                    }

                }
            }
//        }
//    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabView(tabBarItems: List<TabBarItem>, navController: NavHostController) {
    var selectedTabIndex by rememberSaveable { mutableStateOf(0) }

    NavigationBar {
        // Looping over each tab to generate the views and navigation for each item
        tabBarItems.forEachIndexed { index, tabBarItem ->
            NavigationBarItem(
                selected = selectedTabIndex == index,
                onClick = {
                    if (selectedTabIndex != index) {
                        selectedTabIndex = index
                        navController.navigate(tabBarItem.title) {
                            popUpTo = navController.graph.startDestinationId
                            launchSingleTop = true
                        }
                    }
                },
                icon = {
                    TabBarIconView(
                        isSelected = selectedTabIndex == index,
                        selectedIcon = tabBarItem.selectedIcon,
                        unselectedIcon = tabBarItem.unselectedIcon,
                        badgeAmount = tabBarItem.badgeAmount
                    )
                },
                label = { Text(tabBarItem.title)
             }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabBarIconView(
    isSelected: Boolean,
    selectedIcon: ImageVector,
    unselectedIcon: ImageVector,
    badgeAmount: Int? = null
) {
    BadgedBox(badge = { TabBarBadgeView(badgeAmount) }) {
        Icon(
            imageVector = if (isSelected) selectedIcon else unselectedIcon,
            contentDescription = null
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabBarBadgeView(count: Int? = null) {
    count?.let {
        Badge { Text(it.toString()) }
    }
}


