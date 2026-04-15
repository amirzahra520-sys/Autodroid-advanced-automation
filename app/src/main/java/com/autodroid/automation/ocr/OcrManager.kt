package com.autodroid.automation.ocr

import android.graphics.Bitmap
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import kotlinx.coroutines.tasks.await

class OcrManager {
    private val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

    suspend fun recognizeText(bitmap: Bitmap): String {
        val image = InputImage.fromBitmap(bitmap, 0)
        return try {
            val result = recognizer.process(image).await()
            result.text
        } catch (e: Exception) {
            ""
        }
    }

    suspend fun findTextPosition(bitmap: Bitmap, targetText: String): List<android.graphics.Rect> {
        val image = InputImage.fromBitmap(bitmap, 0)
        val result = recognizer.process(image).await()
        return result.textBlocks
            .flatMap { it.lines }
            .flatMap { it.elements }
            .filter { it.text.contains(targetText, ignoreCase = true) }
            .mapNotNull { it.boundingBox }
    }
}
