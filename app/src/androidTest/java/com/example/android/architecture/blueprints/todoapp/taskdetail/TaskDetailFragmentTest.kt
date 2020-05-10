package com.example.android.architecture.blueprints.todoapp.taskdetail

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.data.Task
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
/*
* Marks this test as a test that takes a medium time to run.
* Generally, Unit tests are annotated with @SmallTest, integration tests - with @MediumTest & end-to-end tests - with @LargeTest.
* These annotations help us group and choose which size of tests to run when running tests
*/
@MediumTest
class TaskDetailFragmentTest {

    @Test
    fun activeDetails_DisplayedInUi() {
        val activeTask = Task("Active task", "Bla", false)

        val bundle = TaskDetailFragmentArgs(activeTask.id).toBundle()
        launchFragmentInContainer<TaskDetailFragment>(bundle, R.style.AppTheme)


    }
}