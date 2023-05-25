package com.example.myfirebaseapplication.Utility

import com.example.myfirebaseapplication.model.Questions

object Constant {
    const val USER_NAME:String="user_name"
    const val TOTAL_QUESTION:String="total_question"
    const val CORRECT_ANSWER:String="correct_answer"
    fun  getQuestionList():ArrayList<Questions>{
        val questionList=ArrayList<Questions>()
        val question=Questions(1,"Choose the correct option related to Android.","Android is a web browser",
            "Android is an operating system","Android is a web browser","None of above",2

        )
        questionList.add(question)
        val question2=Questions(2,"How to stop the services in android?","finish()",
            "system.exit().","By manually","stopSelf() and stopService()",4

        )
        questionList.add(question2)
        val question3=Questions(3,"Among the below virtual machines choose the one which is used by the Android operating system?","Dalvik Operating System",
            "JVM.","Simple Virtual Machine","None",1

        )
        questionList.add(question3)
        val question4=Questions(4,"What are the wake locks available in android?","PARTIAL_WAKE_LOCK",
            "SCREEN_DIM_WAKE_LOCK","SCREEN_BRIGHT_WAKE_LOCK","FULL_WAKE_LOCK",4

        )
        questionList.add(question4)
        val question5=Questions(5,"What is log message in android?","Log message is used to debug a program.",
            "Same as printf()","Same as Toast()."," None of the above.",1

        )
        questionList.add(question5)
        val question6=Questions(6,"How to upgrade SQlite the database from a lower version to higher version in android SQlite?"," Using cursor",
            "Using helper Class"," Using intent","None of the above.",2

        )
        questionList.add(question6)
        val question7=Questions(7,"What are the functionalities of HTTP Client interface in android?","Connection management",
            " Cookies management","Authentication management","All of the above",4

        )
        questionList.add(question7)
        val question8=Questions(8,"How to stop the services in android?","stop()",
            "stopServices()","start()"," stopService()",4

        )
        questionList.add(question8)

        val question9=Questions(9,"What is transient data in android?","Permanent data",
            "Secure data","Temporary data","All of the above",3

        )
        questionList.add(question9)

        val question10=Questions(10,"What is the main difference between set and list in android?","Both are same",
            "Set can't contain duplicate values","List may contain duplicate values"," B & C",4)
        questionList.add(question10)

        val question11=Questions(11,
                "Choose the option below which are part of the system library and android runtime layer of Android architecture?"
              ,"SSL",
            "DVM","Webkit"," All of the above",4)
        questionList.add(question11)

        val question12=Questions(12,"DVM was developed by Dan Bornstein.","Data base Server",
            "Browser Engine","Database Engine","None",2)
        questionList.add(question12)

        val question13=Questions(13,"Choose the correct technique to refresh the dynamic content in Android?","Java",
            "Android","Ajax","None",3)
        questionList.add(question13)

        val question14=Questions(14,"JSON stands for?","JavaScript Oriented Notation",
            "Javascript Object Notation","Javascript Object Native"," None",2)
        questionList.add(question14)






        val question15=Questions(15,
                "Identify among the one which is used to log debug messages.","Log.R()",
            "Log.d()","Log.D()","Log.r()",2)
        questionList.add(question15)

        val question16=Questions(16,
            "Identify among the following which is not a state in the service lifecycle?"
            ,"Running",
            "Start","Pause"," Destroy",3)
        questionList.add(question16)

        val question17=Questions(17,"When was Open Handset Alliance announced?","2004",
            "2007","2005","2006",2)
        questionList.add(question17)

        val question18=Questions(18,"Choose the option which is contained in the src folder?","Manifest",
            "Java Source Code","Xml","None",2)
        questionList.add(question18)

        val question19=Questions(19,
                "Among the following choose the one which converts Java bytecode into Dalvik bytecode?","Dex Compiler",
            "Mobile Interpretive","Dalvik Converter"," None",1)
        questionList.add(question19)

        val question20=Questions(20,"State whether true or false:  Android is built upon the Java micro edition(J2ME) version of Java?",
            "TRUE","FALSE"," None","All of them",2)
        questionList.add(question20)


        val question21=Questions(21,
            " What is breakpoint in android?","Breaks the application",
            "Breaks the development code","Breaks the execution.","None of the above.",3)
        questionList.add(question21)

        val question22=Questions(22,
            "What is off-line synchronization in android?"
            ,"Synchronization with internet",
            "Background synchronization","Synchronization without internet"," None of the above",3)
        questionList.add(question22)

        val question23=Questions(23," How many threads are there in asyncTask in android?","Only one",
            "None of the Above"," Two","AsyncTask doesn't have tread",1)
        questionList.add(question23)

        val question24=Questions(24," What is an anonymous class in android?","Interface class",
            " A class that does not have a name but have functionalities in it","Java class","Manifest file",2)
        questionList.add(question24)

        val question25=Questions(25,
            " Fragment in Android can be found through?","findByID()",
            "findFragmentByID()","getContext.findFragmentByID()"," FragmentManager.findFragmentByID()",4)
        questionList.add(question25)

        val question26=Questions(26," What is the purpose of super.onCreate() in android?",
            "To create an activity"," To create a graphical window for subclass"," It allows the developers to write the program","All of them",2)
        questionList.add(question26)


        val question27=Questions(27,"How to move services to foreground in android?","Services always work in Foreground only",
            "No,We can't do this query"," Using startService(Intent intent)","startFordgroud(int id, Notification notification).",4)
        questionList.add(question27)

        val question28=Questions(28,
            " What is the use of content provider in android?","To send the data from an application to another application",
            "To store the data in a database","To share the data between applications"," None",3)
        questionList.add(question28)

        val question29=Questions(29,"Who develops DVM?",
            "Dan Bornstein","Dennis Ritachie"," Linus Torvald","None",1)
        questionList.add(question29)

        val question30=Questions(30,
                "Choose the correct option to find the JSON element length in Android JSON?",
            "sum()","add()"," length()","count()",3)
        questionList.add(question30)


        val question31=Questions(31,
            " Which of the following method is used by services to clean up any services?","stopSelf() method",
            "onStop() method","stopService() method"," onDestroy() method",4)
        questionList.add(question31)

        val question32=Questions(32,"  Which of the following class in android executes the task asynchronously with your service?",
            "Async"," SyncTask"," AsyncTask","All of them",3)
        questionList.add(question32)


        val question33=Questions(33,"Which of the following layout in android arranges its children into rows and columns?","RelativeLayout",
            "TableLayout"," FrameLayout","None of the above",2)
        questionList.add(question33)

        val question34=Questions(34,
            " Which of the following layout in android aligns all children either vertically or horizontally?","RelativeLayout",
            "TableLayout","LinearLayout"," FrameLayout",3)
        questionList.add(question34)

        val question35=Questions(35,"What is the default value of the orientation attribute in LinearLayout?",
            "Horizontal","Vertical","There is no default value of orientation attribute in LinearLayout","None",1)
        questionList.add(question35)

             questionList.add(question30)


        return questionList

    }


}