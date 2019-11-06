library(arules)
library(arulesViz)
library(datasets)
# Load the data set
data(Groceries)
# Create an item frequency plot for the top 20 items
itemFrequencyPlot(Groceries,topN=20,type="absolute")
# Get the rules
rules <- apriori(Groceries, parameter = list(supp = 0.001, conf = 0.8))
summary(rules)
# Show the top 5 rules, but only 2 digits
options(digits=2)
inspect(rules[1:5])
rules<-sort(rules, by="confidence", decreasing=TRUE)
rules <- apriori(Groceries, parameter = list(supp = 0.001, conf = 0.8,maxlen=3))
redundant <- is.redundant(rules)
redundant <- (rules[!is.redundant(rules)])
redundant
rules<-apriori(data=Groceries, parameter=list(supp=0.001,conf = 0.08), 
               appearance = list(default="lhs",rhs="whole milk"))
rules<-sort(rules, decreasing=TRUE,by="confidence")
inspect(rules[1:5])
plot(rules[1:5],method="graph",interactive=TRUE,shading=NA) 

rules<-apriori(data=Groceries, parameter=list(supp=0.001,conf = 0.15,minlen=2), 
               appearance = list(default="rhs",lhs="whole milk"))
rules<-sort(rules, decreasing=TRUE,by="confidence")
inspect(rules[1:5])
library(arulesViz)
plot(rules[1:5],method="graph",interactive=TRUE,shading=NA) 

