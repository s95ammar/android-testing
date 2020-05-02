package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Test

class StatisticsUtilsTest {
	@Test
	fun getActiveAndCompletedStats_notCompleted_returnsZeroHundred() {
		// GIVEN
		val list = listOf(Task(isCompleted = false))

		// WHEN
		val stats = getActiveAndCompletedStats(list)

		// THEN
//		assertEquals(0f, stats.completedTasksPercent)
//		assertEquals(100f, stats.activeTasksPercent)
		assertThat(stats.completedTasksPercent, `is`(0f))
		assertThat(stats.activeTasksPercent, `is`(100f))
	}

	@Test
	fun getActiveAndCompletedStats_notCompleted_returnsFortySixty() {
		val list = listOf(
				Task(isCompleted = true),
				Task(isCompleted = true),
				Task(isCompleted = false),
				Task(isCompleted = false),
				Task(isCompleted = false)
		)
		val stats = getActiveAndCompletedStats(list)
		assertEquals(40f, stats.completedTasksPercent)
		assertEquals(60f, stats.activeTasksPercent)
	}

	@Test
	fun getActiveAndCompletedStats_empty_returnsZeroZero() {
		val list = emptyList<Task>()
		val stats = getActiveAndCompletedStats(list)
		assertEquals(0f, stats.completedTasksPercent)
		assertEquals(0f, stats.activeTasksPercent)
	}

	@Test
	fun getActiveAndCompletedStats_null_returnsZeroZero() {
		val list = null
		val stats = getActiveAndCompletedStats(list)
		assertEquals(0f, stats.completedTasksPercent)
		assertEquals(0f, stats.activeTasksPercent)
	}


}