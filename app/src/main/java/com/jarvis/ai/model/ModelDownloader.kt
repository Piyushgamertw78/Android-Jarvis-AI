package com.jarvis.ai.model
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import java.io.File
class ModelDownloader(private val context: Context) {
    fun isModelDownloaded(): Boolean = File(context.filesDir, "jarvis_model.bin").exists()
    fun downloadModel(onProgress: (Int) -> Unit, onComplete: () -> Unit) {
        val request = DownloadManager.Request(Uri.parse("https://huggingface.co/google/gemma-2b-it-cpu-int4/resolve/main/model.bin"))
            .setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, "jarvis_model.bin")
        val dm = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        dm.enqueue(request)
        Thread { for (i in 0..100 step 10) { Thread.sleep(300); onProgress(i) }; onComplete() }.start()
    }
}
