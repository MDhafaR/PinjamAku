package org.d3if3068.assesment1.pinjamaku.widgets

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import org.d3if3068.assesment1.pinjamaku.R
import org.d3if3068.assesment1.pinjamaku.data.PinjamState
import org.d3if3068.assesment1.pinjamaku.ui.theme.Utama

@Composable
fun Textarea(
    state: PinjamState,
    error: Boolean
) {

    val borderColor = if (error) Color.Red else Utama
    val placeholderColor = if (error) Color.Red else Color.Gray
    OutlinedTextField(
        value = state.deskripsi.value,
        onValueChange = { state.deskripsi.value = it },
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(top = 8.dp)
            .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(4.dp)),
        placeholder = { Text(text = stringResource(id = R.string.deskripsi), color = placeholderColor) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        )
    )
}