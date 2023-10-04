package com.synavos.androidassignment.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.synavos.androidassignment.R
import com.synavos.androidassignment.data.model.response.age.AgeResponse
import com.synavos.androidassignment.ui.theme.font
import com.synavos.androidassignment.util.nonScaledSp

@Composable
fun UserList(
    usersData: List<AgeResponse>,

    ) {
    LazyColumn(
        contentPadding = PaddingValues(start = 8.dp, end = 8.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(usersData) { content ->
            UserListDetailsItem(
                content,
                1

            )
        }
    }

}


@Composable
fun UserListDetailsItem(
    content: AgeResponse,
    maxLines: Int,
) {
    Card(
        shape = RoundedCornerShape(10),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = Color.White),
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(color = Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 10.dp,
                            topEnd = 10.dp,
                            bottomEnd = 0.dp,
                            bottomStart = 0.dp
                        )
                    )
            ) {
                Column(
                    modifier = Modifier.padding(all = 12.dp)
                ) {
                    //item on
                    CustomText(
                        fontFamily = font,
                        text = content.name,
                        fontSize = 16.nonScaledSp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Start,
                        maxLines = maxLines,
                        overflow = TextOverflow.Ellipsis
//                            color = textColor
                    )
                    CustomText(
                        fontFamily = font,
                        text = "${stringResource(id = R.string.age)} ${content.age}",
                        fontSize = 16.nonScaledSp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Start,
                        maxLines = maxLines,
                        overflow = TextOverflow.Ellipsis
//                            color = textColor
                    )
                    CustomText(
                        fontFamily = font,
                        text = "${stringResource(id = R.string.count)} ${content.count}",
                        fontSize = 16.nonScaledSp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Start,
                        maxLines = maxLines,
                        overflow = TextOverflow.Ellipsis
//                            color = textColor
                    )
                }

            }
        }
    }


}