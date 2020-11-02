package com.app.ticketsupport.ui.ticket;

import android.content.Context;
import android.util.Log;

import com.app.ticketsupport.models.AnswerRequest;
import com.app.ticketsupport.models.IdModel;
import com.app.ticketsupport.models.TicketModel;
import com.app.ticketsupport.models.TicketRequest;
import com.app.ticketsupport.serverConnection.IResponse;
import com.app.ticketsupport.serverConnection.TicketAPI;
import com.app.ticketsupport.serverConnection.TokenClass;
import com.app.ticketsupport.ui.login.ILogin;
/**
 * Get Values from View and make require changes for serverConnection.TicketAPI
 */
public class TicketRepostory {

    private Context context;
    private TokenClass tokenClass;
    private TicketAPI ticketAPI;

    public TicketRepostory(Context context){
        this.context = context;
        tokenClass = new TokenClass(context);
        ticketAPI = new TicketAPI();


    }

    public void NewTicket(String department, String priority, String title, String message, String ticketType, ILogin iLogin){

        String answer = tokenClass.getName() + ": " +message;


        TicketRequest ticketRequest = new TicketRequest(department,priority,title,message,ticketType);

        ticketAPI.NewTicket(getToken(), ticketRequest, new IResponse() {
            @Override
            public void onSuccess(Object response) {
                IdModel idModel = (IdModel) response;
                sendAnswer(answer, idModel.get_id(), new ILogin() {
                    @Override
                    public void onSuccess(Object response) {
                        iLogin.onSuccess("success");
                    }

                    @Override
                    public void onFailure(String Erorr) {
                        iLogin.onFailure(Erorr);

                    }
                });
            }

            @Override
            public void onFailuer(String errorMessage) {
                iLogin.onFailure(errorMessage);

            }
        });




    }


    public void sendAnswer(String message, String id, ILogin iLogin){
        AnswerRequest answerRequest = new AnswerRequest(id,message);
        ticketAPI.SendAnswer(getToken(), answerRequest, new IResponse() {
            @Override
            public void onSuccess(Object response) {
                iLogin.onSuccess(response);
            }

            @Override
            public void onFailuer(String errorMessage) {
                iLogin.onFailure(errorMessage);
            }
        });
    }


    public void getTicket(ILogin iLogin){
        ticketAPI.getTickets(getToken(), new IResponse() {
            @Override
            public void onSuccess(Object response) {
                iLogin.onSuccess(response);
            }

            @Override
            public void onFailuer(String errorMessage) {
                iLogin.onFailure(errorMessage);

            }
        });
    }


    public String getToken(){
        return tokenClass.getToken().getToken();
    }
}
