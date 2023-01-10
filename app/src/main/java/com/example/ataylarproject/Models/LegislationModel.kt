package com.example.ataylarproject.Models


// stufe önemli. ilk sayfada iken stufe 1. her ilerleyen sayfada stufe 1 artar.
//upper level id stufe 1 iken null.

data class LegislationModel
    (
    val id: Int = 0,
    val stufe: Int,
    val text: String,
    val upperLevelId: Int ? = null)
