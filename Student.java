public class Student implements Comparable<Student>
{
	private String name;
	private int id;

	
	public Student(String name, int id) 
	{
        this.name = name;
        this.id = id;
    }

	public String getName()
	{
		return name;
	}
					
	public int getId()
	{
		return id;
	}

	public int compareTo(Student other)
	{
		return other.getId() - this.getId();
	}

    public void setName(String string)
	{
        this.name = string;
    }

	public void setId(int newId)
	{
		this.id = newId;
	}
		
} 