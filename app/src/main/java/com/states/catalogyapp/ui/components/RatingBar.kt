package com.states.catalogyapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.states.catalogyapp.R

@Composable
fun RatingBar(
    rating: Double,
    modifier: Modifier = Modifier,
    maxStars: Int = 5,
    starSize: Dp = 24.dp,
    activeColor: Color = MaterialTheme.colorScheme.primary,
    inactiveColor: Color = MaterialTheme.colorScheme.outline,
    onRatingChange: (Double) -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(4.dp)
    ) {
        for (i in 1..maxStars) {
            val starValue = i.toDouble()
            Icon(
                painter = painterResource(
                    id = if (rating >= starValue) R.drawable.ic_star_filled
                    else if (rating > starValue - 1) R.drawable.ic_star_half
                    else R.drawable.ic_star_outline
                ),
                contentDescription = "Star $i",
                tint = if (rating >= starValue - 0.5) activeColor else inactiveColor,
                modifier = Modifier
                    .clickable { onRatingChange(starValue) }
                    .padding(2.dp)
                    .size(starSize)
            )
        }
    }
}