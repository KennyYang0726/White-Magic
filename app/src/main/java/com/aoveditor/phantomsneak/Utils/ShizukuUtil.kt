package com.aoveditor.phantomsneak.Utils

import android.util.Log
import rikka.shizuku.Shizuku
import java.io.BufferedReader
import java.io.InputStreamReader



class ShizukuUtil {

    companion object {
        @JvmStatic
        fun executeShellCommandWithShizuku(command: String): String? {
            return try {
                val process = Shizuku.newProcess(arrayOf("sh", "-c", command), null, null)

                // Read the output from the process
                val reader = BufferedReader(InputStreamReader(process.inputStream))
                val output = StringBuilder()
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    output.append(line).append("\n")
                }
                reader.close()

                // Wait for the process to finish
                val exitCode = process.waitFor()

                if (exitCode == 0) {
                    Log.d("ShizukuResult", "Command executed successfully.")
                    return output.toString() // 返回執行輸出
                } else if (exitCode == 255) {
                    Log.e("ShizukuResult", "Permission Denied.")
                    return "failed -> Permission Denied." // 失敗 權限不足
                } else if (exitCode == 1) {
                    Log.e("ShizukuResult", "Directory or file does not exist.")
                    return "failed -> Directory or file does not exist." // 失敗 目錄或檔案不存在
                } else {
                    Log.e("ShizukuResult", "Command execution failed with exit code $exitCode.")
                    return "failed -> Command execution failed with exit code $exitCode." // 未知錯誤
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("ShizukuResult", "Error: ${e.message}")
                null // 返回 null 表示出現異常
            }
        }
    }
}
