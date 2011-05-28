

all :
	gradle

clean :
	gradle $@
	$(RM) -rf `find . -name .gradle`
	$(RM) -r mydb.tmp
	$(RM) mydb.*

