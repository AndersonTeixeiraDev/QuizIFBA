package br.com.example.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class QuizActivity extends AppCompatActivity {

    TextView pergunta;
    RadioButton rbResposta1, rbResposta2, rbResposta3, rbResposta4;
    int respostaCerta = R.id.rbResposta1;

    // QUESTÕES COM SUAS RESPECTIVAS RESPOSTAS CORRETAS DECLARADAS
    List<Questao> questoes = new ArrayList<Questao>(){
        {
            add(new Questao("Qual o ano de criação do IFBA-Irecê?", R.id.rbResposta2, "2011", "2012", "2010", "2009"));
            add(new Questao("Como se chamava o IFBA anteriormente?", R.id.rbResposta1, "CEFET", "IFBA", "CETEF", "TECEF"));
            add(new Questao("Qual o nome do presidente responsável por erguer os primeiros IF's?", R.id.rbResposta3, "Luiz Inacio Lula da Silva", "Fernando Henrique Cardoso", "Nilo Peçanha", "Luciano Junior"));
            add(new Questao("Como se chama a zona geográfica em que está está localizado o IFBA-Irece?", R.id.rbResposta1, "zona fisiográfica da Chapada Diamantina Setentrional", "zona fisiográfica da Chapada Diamantina", "zona fisiográfica de Irecê", "zona fisiográfica Setentrional"));
            add(new Questao("Quantos cursos o IFBA possuía em sua inauguração?", R.id.rbResposta4, "2", "3", "4", "5"));
            add(new Questao("Qual a nota do curso ADS frente ao MEC?", R.id.rbResposta3, "2", "3", "4", "5"));
            add(new Questao("Qual o ano de implantação do curso de ADS e MI respectivamente?", R.id.rbResposta1, "2015 e 2018", "2014 e 2018", "2016 e 2019", "2015 e 2017"));
            add(new Questao("Qual a forma de ingresso nos cursos superiores do IFBA Irece?", R.id.rbResposta1, "SISU", "Processo seletivo interno", "SUSI", "SSIO"));
            add(new Questao("É verdadeiro afirmar que o IFBA Irecê já ofertou cursos do Pronatec?", R.id.rbResposta1, "Sim", "Não"));
            add(new Questao("Quantos docentes efetivos o IFBA Irecê possui em seu quadro de profissionais?", R.id.rbResposta1, "49", "55", "45", "47"));
            add(new Questao("Quantos docentes temporários ou substitutos o IFBA Irecê possui em seu quadro de profissionais?", R.id.rbResposta1, "10", "15", "12", "20"));
        }
    };


    //método genérico de carregar a próxima questão na tela
    //Esse método remove a questão também, para que elas não se repitam a cada chamada
    private void carregarQuestao(){
        if(questoes.size() > 0) {
            Questao q = questoes.remove(0);
            pergunta.setText(q.getPergunta());
            List<String> resposta = q.getRespostas();
            rbResposta1.setText(resposta.get(0));
            rbResposta2.setText(resposta.get(1));
            rbResposta3.setText(resposta.get(2));
            rbResposta4.setText(resposta.get(3));
            respostaCerta = q.getRespostaCerta();
            rgRespostas.setSelected(false);

        }
        else{ //É chamado após acabar as questões
            Intent intent = new Intent(this, RespostaActivity.class);
            intent.putExtra("pontos", pontos);
            startActivity(intent);
            finish();
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pergunta = (TextView)findViewById(R.id.pergunta);
        rbResposta1 = (RadioButton)findViewById(R.id.rbResposta1);
        rbResposta2 = (RadioButton)findViewById(R.id.rbResposta2);
        rbResposta3 = (RadioButton)findViewById(R.id.rbResposta3);
        rbResposta4 = (RadioButton)findViewById(R.id.rbResposta4);
        carregarQuestao();
    }

    public void btnResponderOnClick(View v){
        RadioGroup rgRespostas = (RadioGroup)findViewById(R.id.rgRespostas);
        Intent intent = new Intent(this, RespostaActivity.class);
        intent.putExtra("Acertou!!", rgRespostas.getCheckedRadioButtonId() == R.id.rbResposta1);
        startActivity(intent);
    }


    //Este método é chamado quando volta-se a tela do quiz para exibir outra pergunta
    // Troca-se uma pergunta por outra

    @Override
    protected void onRestart(){
        super.onRestart();
        carregarQuestao();

    }

    public void btnResponderOnClick(View v){
        RadioButton rb = (RadioButton)findViewById(rgRespostas.getCheckedRadioButtonId());
        Intent intent = new Intent(this, RespostaActivity.class);
        if(rgRespostas.getCheckedRadioButtonId() == respostaCerta) {
            intent.putExtra("acertou", true);
            pontos++;
        }
        else intent.putExtra("acertou", false);
        intent.putExtra("pontos", pontos);
        startActivity(intent);
        rb.setChecked(false);
    }



}
