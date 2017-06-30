package br.com.inf.vacinado.Controller;

import android.widget.EditText;

import br.com.inf.vacinado.View.Cadastro;

public class MascaraCpf {

    /**
     * Método responsável por realizar o controle da máscara do CPF no cadastro de novo usuário
     *
     * @param edit_cpf   : EditText do CPF que o usuário irá utilizar para digitar o CPF
     * @param isUpdating : Booleano utilizado para identificar quando o usuário está digitando e
     *                   quando ele não está. Variável para evitar chamada infinita do método.
     */
    public static void ControlarMascaraCpf(boolean isUpdating, EditText edit_cpf, CharSequence s, int start, int before, int after) {

        if (isUpdating) {
            Cadastro.setIsUpdating(false);
            return;
        }

        //hasMask: Ao apagar o texto, a máscara é removida, então o posicionamento do cursor
        // precisa saber se o texto atual tinha ou não a máscara
        boolean hasMask = s.toString().indexOf('.') > -1 || s.toString().indexOf('-') > -1;

        //Remove os '.' e o '-' da String
        String str = s.toString().replaceAll("[.]", "").replaceAll("[-]", "");

        //Os parâmetros before e after dizem o tamanho anterior e atual da String digitada.
        //If: After > Before = Usuário está digitando
        //Else: Usuário está apagando
        if (after > before) {

            str = calcularMascara(str);

            //Altera o novo texto para o usuário
            edit_cpf.setText(str);

            //Seta a posicao do curso
            edit_cpf.setSelection(edit_cpf.getText().length());

        } else {

            Cadastro.setIsUpdating(true);
            edit_cpf.setText(str);

            //Se estiver apagando posiciona o cursor no local correto.
            //Isso irá tratar a deleção dos caracteres da mascara
            edit_cpf.setSelection(Math.max(0, Math.min(hasMask ? start - before : start, str.length())));

            str = calcularMascara(str);

            //Altera o novo texto para o usuário
            edit_cpf.setText(str);

            //Seta a posicao do curso
            edit_cpf.setSelection(edit_cpf.getText().length());
        }
    }

    /**
     * @param str String digitada pelo usuário sem os caracteres "." e "-"
     * @return Retorna uma String já com os caracteres "." e "-"
     */
    private static String calcularMascara(String str) {

        //Se tem mais de 9 caracteres (sem máscara) oloca os '.' e o '-'
        if (str.length() > 9) {
            str = str.substring(0, 3) + '.' + str.substring(3, 6) + '.' + str.substring(6, 9) + '-' + str.substring(9);

            //Se tem mais de 6, coloca o '.' e o '-'
        } else if (str.length() > 6) {
            str = str.substring(0, 3) + '.' + str.substring(3, 6) + '.' + str.substring(6);

            //Se tem mais de 3, coloca só o '.'
        } else if (str.length() > 3) {
            str = str.substring(0, 3) + '.' + str.substring(3);
        }

        //Modifica a flag para evitar chamada infinita
        Cadastro.setIsUpdating(true);
        return str;
    }
}
