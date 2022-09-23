package shadow.huntrip.myapplication.addition;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;

import shadow.huntrip.myapplication.MainActivity;

public class CustomDialog
{
    public static void ShowPositiveDialog(Context appContext, Class<?> transition, Activity action,
                                          int correctAnswersCount)
    {
        new AlertDialog.Builder(appContext).setTitle("Важное сообщение")
                .setMessage("Поздравляю, вы набрали " + correctAnswersCount
                        + " баллов. Желаете ли вы перейти на следующий уровень?")
                .setPositiveButton(android.R.string.ok, (dialogInterface, i) ->
                {
                    action.finish();
                    appContext.startActivity(new Intent(appContext, transition));
                })
                .setNegativeButton(android.R.string.cancel, (dialogInterface, i) ->
                {
                    action.finish();
                    appContext.startActivity(new Intent(appContext, MainActivity.class));
                })
                .setIcon(android.R.drawable.ic_dialog_alert).show();
    }

    public static void ShowNegativeDialog(Context appContext, Class<?> transition, Activity action,
                                          int correctAnswersCount, String decline)
    {
        new AlertDialog.Builder(appContext).setTitle("Важное сообщение")
                .setMessage("Поздравляю, вы набрали " + correctAnswersCount
                        + " " + decline +". Это капец как мало! Пробуйте ещё раз!")
                .setPositiveButton(android.R.string.ok, (dialogInterface, i) ->
                {
                    action.finish();
                    appContext.startActivity(new Intent(appContext, transition));
                })
                .setNegativeButton(android.R.string.cancel, (dialogInterface, i) ->
                {
                    action.finish();
                    appContext.startActivity(new Intent(appContext, MainActivity.class));
                })
                .setIcon(android.R.drawable.ic_dialog_alert).show();
    }

    public static void ShowFinalPositiveDialog(Context appContext, Class<?> transition, Activity action,
                                               int correctAnswersCount)
    {
        new AlertDialog.Builder(appContext).setTitle("Важное сообщение")
                .setMessage("Поздравляю, вы набрали " + correctAnswersCount
                        + " баллов. Вы полностью прошли 1 часть игры! Желаем Вам дальнейших успехов в следующей части! " +
                        "С уважением, " + "ShadowHuntRip")
                .setPositiveButton(android.R.string.ok, (dialogInterface, i) ->
                {
                    action.finish();
                    appContext.startActivity(new Intent(appContext, transition));
                })
                .setNegativeButton(android.R.string.cancel, (dialogInterface, i) ->
                {
                    action.finish();
                    appContext.startActivity(new Intent(appContext, MainActivity.class));
                })
                .setIcon(android.R.drawable.ic_dialog_alert).show();
    }
}
