package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.ViewModelProvider
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.getOrAwaitValue
import org.hamcrest.Matchers.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/*
* Uses AndroidJUnit4 test runner instead of the default JUnit4 test runner.
* Allows AndroidX Test to run tests differently, depending on whether it's an instrumented test or a local test.
*/
@RunWith(AndroidJUnit4::class)
class TasksViewModelTest {

/*
* To use AndroidX classes in local tests:
*       1. Add AndroidX Test - JVM testing dependencies
*       2. Add Roboelectric dependency
*       3. Use AndroidX Test
*       4. Add AndroidJUnit4 test runner
*       5. Add to build.gradle in 'android {...}': testOptions.unitTests.includeAndroidResources = true
*/

/*
* To test LiveData:
*       1. Add architecture components testing dependency
*       2. Add InstantTaskExecutorRule
*       3. Observe the LiveData (use getOrAwaitValue() ext fun instead of observeForever())
*/

//	Runs all architecture components bg jobs in the same thread. This ensures that the test results happen synchronously and in a repeatable order.
	@get:Rule
	var instantExecutorRule = InstantTaskExecutorRule()

// Subject under test
	private lateinit var tasksViewModel: TasksViewModel

//	Runs this method before each @Test method / before all @Test methods
	@Before
	fun setupViewModel() {
		tasksViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())
	}

	@Test
	fun addNewTask_setsNewTaskEvent() {

//		val tasksViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())

		tasksViewModel.addNewTask()

		val value = tasksViewModel.newTaskEvent.getOrAwaitValue()
		assertThat(value.getContentIfNotHandled(), not(nullValue()))

	}

	@Test
	fun setFilterAllTasks_tasksAddViewVisible() {
		tasksViewModel.setFiltering(TasksFilterType.ALL_TASKS)
		val value = tasksViewModel.tasksAddViewVisible.getOrAwaitValue()
		assertThat(value, `is`(true))
	}

}