package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Test

class StatisticsUtilsTest {
	@Test
	fun getActiveAndCompletedStats_noCompleted_returnsHundredZero() {
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
	fun getActiveAndCompletedStats_noActive_returnsZeroHundred() {
		val list = listOf(
				Task(isCompleted = true)
		)
		val stats = getActiveAndCompletedStats(list)
		assertEquals(0f, stats.activeTasksPercent)
		assertEquals(100f, stats.completedTasksPercent)
	}

	@Test
	fun getActiveAndCompletedStats_both_returnsFortySixty() {
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
	fun getActiveAndCompletedStats_error_returnsZeros() {
		val stats = getActiveAndCompletedStats(null)
		assertEquals(0f, stats.completedTasksPercent)
		assertEquals(0f, stats.activeTasksPercent)
	}

	@Test
	fun getActiveAndCompletedStats_empty_returnsZeros() {
		val stats = getActiveAndCompletedStats(emptyList())
		assertEquals(0f, stats.completedTasksPercent)
		assertEquals(0f, stats.activeTasksPercent)
	}

}