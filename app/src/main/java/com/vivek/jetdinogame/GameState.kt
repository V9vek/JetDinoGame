package com.vivek.jetdinogame

data class GameState(private val status: Status = Status.START) {
    enum class Status {
        START,
        RUNNING,
        FINISH
    }

    fun isStarting() = status == Status.START
    fun isRunning() = status == Status.RUNNING
    fun isFinished() = status == Status.FINISH
}