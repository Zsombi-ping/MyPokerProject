package com.example.mypokerproject;

public class Questions
{
    private String questionname;
    private String status;

    public Questions(String qn)
    {
        this.questionname=qn;
    }

    public Questions(){}

    public String getQuestionname()
    {
        return questionname;
    }

    public void setQuestionname(String questionname)
    {
        this.questionname = questionname;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
}
