package com.course.quiz.source.locals

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.course.quiz.data.models.*
import com.course.quiz.source.locals.dao.AnswersDAO
import com.course.quiz.source.locals.dao.QuestionsAnswersDAO
import com.course.quiz.source.locals.dao.QuestionsDAO
import com.course.quiz.util.ioThread

@Database(
    entities = [Question::class, Answer::class, QuestionAnswers::class],
    version = 1,
    exportSchema = false
)
abstract class QuizDB : RoomDatabase() {
    abstract fun getQuestionsDao(): QuestionsDAO
    abstract fun getQuestionAnswerDao(): QuestionsAnswersDAO
    abstract fun getAnswersDao(): AnswersDAO

    companion object {
        @Volatile
        private var Instance: QuizDB? = null

        fun getDatabase(context: Context): QuizDB {
            return Instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuizDB::class.java,
                    "quiz_db"
                )
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            ioThread {
                                getDatabase(context).getQuestionsDao().insert(PREPOPULATE_QUESTIONS)
                                getDatabase(context).getQuestionAnswerDao()
                                    .insert(PREPOPULATE_QUESTIONS_ANSWERS)

                            }
                        }
                    }).allowMainThreadQueries().build()
                Instance = instance

                instance
            }

        }

        val PREPOPULATE_QUESTIONS = listOf(
            Question(
                1,
                " Kotlin interfaces and abstract classes are very similar. What is one thing abstract class can do that interfaces cannot?",
                QuestionType.SINGLE,
                "2"
            ),
            Question(
                2,
                " Inside an extension function, what is the name of the variable that corresponds to the receiver object",
                QuestionType.SINGLE,
                "2"
            ),
            Question(3, "What is the entry point for a Kotlin application?", QuestionType.SINGLE, "0"),
            Question(
                4,
                " You are writing a console app in Kotlin that processes test entered by the user. If the user enters an empty string, the program exits. Which kind of loop would work best for this app? Keep in mind that the loop is entered at least once",
                QuestionType.MULTI,
                "1200"
            ),
            Question(
                5,
                " You are attempting to assign an integer variable to a long variable, but Kotlin compiler flags it as an error. Why?",
                QuestionType.MULTI,
                "1234"
            ),
            Question(
                6,
                "Which line of code shows how to display a nullable string's length and shows 0 instead of null?",
                QuestionType.SINGLE,
                "1"
            ),
            Question(7, "How can you declare a variable in Kotlin?", QuestionType.SINGLE, "3"),
            Question(
                8,
                "How do you insert COMMENTS in Kotlin code?",
                QuestionType.MULTI,
                "1030"
            ),
            Question(
                9,
                "On which of the following, developers can test the application, during developing the android applications?",
                QuestionType.SINGLE,
                "3"
            ),
            Question(
                10,
                "What is the equivalent of Java static methods in Kotlin?",
                QuestionType.MULTI,
                "1234"
            ),
            Question(
                11,
                "A(n)........... represents an object or concept, and its properties, to store in a database.",
                QuestionType.SINGLE,
                "0"
            ),
            Question(
                12,
                "Which tag is used to define themes?",
                QuestionType.SINGLE,
                "1"
            ),
            Question(
                13,
                "Which of the following are reasons for using Material Design components? Select all that apply.",
                QuestionType.MULTI,
                "1230"
            ),
            Question(
                14,
                "Which of the following accessibility tools are available on Android devices?",
                QuestionType.MULTI,
                "1200"
            ),
            Question(
                15,
                "Assume you have a TextView with a textSize attribute of 16sp. This TextView also has a style applied to it which sets textSize to 14sp. In addition," +
                        " there is also a theme in the app that sets textSize to 12sp. What is the actual textSize of the TextView that will be displayed on screen?",
                QuestionType.SINGLE,
                "3"
            ),
        )

        private val PREPOPULATE_QUESTIONS_ANSWERS = listOf(
            QuestionAnswers(1, " Only abstract classes are inheritable by subclasses", false, false),
            QuestionAnswers(1, " Only abstract classes can inherit from multiple superclasses", false, false),
            QuestionAnswers(1, " Only abstract classes can have abstract methods", false, false),
            QuestionAnswers(1, " Only abstract classes can store state", false, true),
            QuestionAnswers(2, "The variable is named it", false, false),
            QuestionAnswers(2, " The variable is named this", false, true),
            QuestionAnswers(2, " The variable is named receiver", false, false),
            QuestionAnswers(2, " The variable is named default", false, false),
            QuestionAnswers(3, " fun static main(){}", false, false),
            QuestionAnswers(3, " fun main(){}", false, true),
            QuestionAnswers(3, " fun Main(){}", false, false),
            QuestionAnswers(3, " public static void main(){}", false, false),
            QuestionAnswers(4, " a do..while loop", false, true),
            QuestionAnswers(4, " a for loop", false, false),
            QuestionAnswers(4, " a while loop", false, false),
            QuestionAnswers(4, " a forEach loop", false, false),
            QuestionAnswers(5, " You must wrap all implicit conversion in a try/catch block", false, false),
            QuestionAnswers(5, " You can only assign Long to an Int, not the other way around", false, false),
            QuestionAnswers(5, " There is no implicit conversion from Int to Long", false, true),
            QuestionAnswers(5, " All integers in Kotlin are of type Long", false, false),
            QuestionAnswers(6, " println(b!!.length ?: 0)", false, false),
            QuestionAnswers(6, " println(b?.length ?: 0)", false, true),
            QuestionAnswers(6, " println(b?.length ?? 0)", false, false),
            QuestionAnswers(6, " println(b == null? 0: b.length)", false, false),
            QuestionAnswers(7, "value my_var: Char", false, false),
            QuestionAnswers(7, "value Char : my_var", false, false),
            QuestionAnswers(7, "my_var: Char", false, false),
            QuestionAnswers(7, "value my_var: Char", false, true),
            QuestionAnswers(8, "/* This is a comment", false, true),
            QuestionAnswers(8, "# This is a comment", false, false),
            QuestionAnswers(8, "// This is a comment", false, true),
            QuestionAnswers(8, "-- This is a comment", false, false),
            QuestionAnswers(9, "Third-party emulators", false, false),
            QuestionAnswers(9, "Physical android phone", false, false),
            QuestionAnswers(9, "Emulator included in Android SDK", false, false),
            QuestionAnswers(9, "All of the above", false, true),
            QuestionAnswers(10, "companion object", false, true),
            QuestionAnswers(10, "package-level function", false, true),
            QuestionAnswers(10, "object", false, true),
            QuestionAnswers(10, "all of the above", false, true),
            QuestionAnswers(11, "Entity", false, true),
            QuestionAnswers(11, "Object", false, false),
            QuestionAnswers(11, "Class", false, false),
            QuestionAnswers(11, "Row", false, false),
            QuestionAnswers(12, "<style>", false, false),
            QuestionAnswers(12, "<theme>", false, true),
            QuestionAnswers(12, "<meta-tag>", false, false),
            QuestionAnswers(12, "<styling>", false, false),
            QuestionAnswers(
                13,
                "They are designed to be beautiful, functional, and work together.",
                false,true
            ),
            QuestionAnswers(13, "They help you create an app that uses consistent styling", false,true),
            QuestionAnswers(13, "They help you make your app more accessible for all users", false,true),
            QuestionAnswers(
                13,
                "Android Studio will give you a warning if you are using a poor color scheme.",
                false,false
            ),
            QuestionAnswers(14, "TalkBack", false,true),
            QuestionAnswers(14, "Accessibility Scanner", false,true),
            QuestionAnswers(
                14,
                "In Android Studio, Refactor > Add RTL support where possible",
                false,false
            ),
            QuestionAnswers(14, "Lint", false,false),
            QuestionAnswers(15, "12sp)", false,false),
            QuestionAnswers(15, "14sp)", false,false),
            QuestionAnswers(15, "11sp)", false,false),
            QuestionAnswers(15, "16sp)", false,true),
        )


    }
}
