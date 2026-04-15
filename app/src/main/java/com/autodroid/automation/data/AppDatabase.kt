package com.autodroid.automation.data

import androidx.room.*

@Entity(tableName = "scripts")
data class Script(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val description: String,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "instructions")
data class Instruction(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val scriptId: Long,
    val type: InstructionType,
    val params: String, // JSON or structured string
    val order: Int
)

enum class InstructionType {
    CLICK_TEXT, CLICK_COORDS, READ_TEXT, SET_VARIABLE, IF_CONDITION, LOOP_START, LOOP_END, DELAY, OCR_SCAN
}

@Entity(tableName = "variables")
data class Variable(
    @PrimaryKey val name: String,
    val value: String,
    val type: String // "string", "int", "bool"
)

@Dao
interface ScriptDao {
    @Query("SELECT * FROM scripts")
    suspend fun getAllScripts(): List<Script>

    @Insert
    suspend fun insertScript(script: Script): Long

    @Query("SELECT * FROM instructions WHERE scriptId = :scriptId ORDER BY `order` ASC")
    suspend fun getInstructionsForScript(scriptId: Long): List<Instruction>

    @Insert
    suspend fun insertInstruction(instruction: Instruction)
}

@Database(entities = [Script::class, Instruction::class, Variable::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun scriptDao(): ScriptDao
}
