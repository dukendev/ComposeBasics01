package com.ysanjeet535.composelazycolumnwithexpandablecards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ysanjeet535.composelazycolumnwithexpandablecards.ui.theme.ComposeLazyColumnWithExpandableCardsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLazyColumnWithExpandableCardsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    val viewModel = hiltViewModel<MainViewModel>()
    ListView(list = viewModel.getDummyList())

}

@Composable
fun ListView(list: List<Item>) {
    LazyColumn {
        items(list) { item ->
            ExpendableCard(item = item)
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ExpendableCard(item: Item) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessVeryLow
                )
            )
    ) {

        Column(
            modifier = Modifier
                .padding(8.dp)
                .background(Color.Cyan.copy(alpha = 0.1f))
                .fillMaxWidth()
                .shadow(elevation = 1.dp, shape = RoundedCornerShape(2.dp))
        ) {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = item.name, style = MaterialTheme.typography.body1)
                IconButton(onClick = { isExpanded = !isExpanded }) {
                    Icon(
                        if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
            }

            Text(
                modifier = Modifier.padding(8.dp),
                text = item.age.toString(),
                style = MaterialTheme.typography.body2
            )

            AnimatedVisibility(visible = isExpanded) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = item.description,
                    style = MaterialTheme.typography.h1
                )
            }


        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeLazyColumnWithExpandableCardsTheme {
        ExpendableCard(
            item = Item(
                "Sanjeet",
                24,
                "We recommend using the Navigation Compose library to add navigation elements to your Compose projects. These elements let you add UI to navigate between composables while taking advantage of the Navigation component’s infrastructure and features."
            )
        )
    }
}