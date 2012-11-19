repositories.remote << 'http://repo1.maven.org/maven2/'

Project.local_task :javadoc

define 'ABCD' do
    project.version = '0.1.0'
    compile.with 'org.apache.commons:commons-math:jar:2.2'
    package :jar

    task :javadoc do
        system 'javadoc -d ./javadoc -sourcepath ./src -subpackages main'
    end
end

my_layout = Layout.new
my_layout[:source, :main, :java] = 'src/main'
my_layout[:source, :main, :resources] = 'src/resources'

define 'foo', :layout=>my_layout