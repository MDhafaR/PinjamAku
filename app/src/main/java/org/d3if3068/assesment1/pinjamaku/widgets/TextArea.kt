package org.d3if3068.assesment1.pinjamaku.widgets

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.d3if3068.assesment1.pinjamaku.ui.theme.PinjamAkuTheme
import org.d3if3068.assesment1.pinjamaku.ui.theme.Utama

@Composable
fun Textarea() {
    val text = rememberSaveable { mutableStateOf("") }
    OutlinedTextField(
        value = text.value,
        onValueChange = { text.value = it },
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .border(width = 1.dp, color = Utama, shape = RoundedCornerShape(4.dp)),
        placeholder = { Text(text = "Deskripsi", color = Color.Gray)},
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        )
    )
}

@Preview(showBackground = true)
@Composable
fun TextAreaPrev() {
    PinjamAkuTheme {
        Textarea()
    }
}