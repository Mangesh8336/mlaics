
import weka.core.Instances;
//import weka.classifiers.*;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.*;//Evaluation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.Exception;
import java.math.*;

public class prg2
{
	public prg2()
	{
		try
		{
		BufferedReader trainReader = new BufferedReader(new FileReader("/home/user/Machine Learning and Applications/NAIVEBAYES/iris.arff"));
			
			Instances trainingSet = new Instances(trainReader);
			trainingSet.randomize(new java.util.Random(0));

			int trainsize = (int) Math.round(trainingSet.numInstances()*0.8);
			int testsize = trainingSet.numInstances() - trainsize;

			
			Instances train = new Instances(trainingSet,0,trainsize);
			Instances test = new Instances(trainingSet,trainsize,testsize);

			train.setClassIndex(train.numAttributes() - 1);
			test.setClassIndex(test.numAttributes() - 1);			

			NaiveBayes model=new NaiveBayes();
			model.buildClassifier(train);
			Evaluation eTest = new Evaluation(test);
			eTest.evaluateModel(model, test);
			String[] cmarray = {"Iris-setosa","Iris-versicolor","Iris-virginica"};
			String[] cmarray = {"tested_positive","tested_negative"};
			ConfusionMatrix cm = new ConfusionMatrix(cmarray);
			

			for (int i = 0; i < test.numInstances(); i++)
			{
				test.instance(i).setClassMissing();
				double cls = model.classifyInstance(test.instance(i));
				test.instance(i).setClassValue(cls);
			}

			System.out.println("Error Rate: "+eTest.errorRate()*100);
			System.out.println("Pct Correct: (accuracy)"+eTest.pctCorrect());

			for (int i=0; i<train.numClasses(); i++){
				System.out.println("Class "+ i);
				System.out.println("Precision " +eTest.precision(i));
				System.out.println("Recall "+eTest.recall(i));
				System.out.println("TPR : "+eTest.truePositiveRate(i));
				System.out.println("FPR : "+eTest.falsePositiveRate(i));
				//System.out.println("Area under ROC "+ eTest.areaUnderROC(i));
				System.out.println();
			}
			
		}
		catch (Exception o)
		{
			System.err.println(o.getMessage());
		}
	}

	public static void main(String[] args) {
		prg2 nb = new prg2();
	}
}
