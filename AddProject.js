import React, { Component } from 'react'
import PropType from "prop-types";
import {connect} from "react-redux";
import {createProject} from "../../actions/projectActions";
import classnames from "classnames"
 class AddProject extends Component {

      constructor(){
         super();
         this.state ={
            projectName: "",
            projectIdentifir: "",
            description: "",
            create_date: "",
            upate_date:"",
            errors:{}
                    };
            this.onChange=this.onChange.bind(this) ; 
            this.onSubmit=this.onSubmit.bind(this);      
                }
             //Lifecyclehooks
            componentWillReceiveProps(nextProps)
            {
                if(nextProps.errors)
                {
                    this.setState({errors:nextProps.errors});
                }

            }
            onChange(e)
            {
                this.setState({[e.target.name]:e.target.value});
            }
            onSubmit(e) 
            {
                e.preventDefault();
                const newProject={
                    projectName: this.state.projectName,
            projectIdentifir: this.state.projectIdentifir,
            description: this.state.description,
            create_date: this.state.create_date,
            upate_date:  this.state.upate_date
                };
                //console.log(newProject);
              //  console.log("hello");
              this.props.createProject(newProject,this.props.history)
              console.log(newProject);
              
            }   
    render() {
        const {errors}=this.state;
        return (
            <div>
                
            <div className="project">
        <div className="container">
            <div className="row">
                <div className="col-md-8 m-auto">
                    <h6 className="display-4 text-center">Create / Edit Project form</h6>
                    <hr />
                    <form onSubmit={this.onSubmit}>
                        <div className="form-group">
                            <input type="text" className={classnames("form-control form-control-lg ",{
                                "is-invalid":errors.projectName
                            })} placeholder="Project Name" name="projectName"
                            value={this.state.projectName} 
                            onChange={this.onChange}
                            />
                            {/* <p>{errors.projectName}</p> */}
                    {errors.projectName && (
                      <div className="invalid-feedback">
                        {errors.projectName}
                      </div>
                    )}
                        </div>
                        <div className="form-group">
                            <input type="text" className={classnames("form-control form-control-lg ",{
                                "is-invalid":errors.projectIdentifir})}
                            placeholder="Unique Project ID" name="projectIdentifir" 
                            value={this.state.projectIdentifir} onChange={this.onChange}
                                 />
                            {errors.projectIdentifir &&  (<div className="invalid-feedback">{errors.projectIdentifir}</div>)}  
                     
                        </div>
                
                        <div className="form-group">
                            <textarea className={classnames("form-control form-control-lg ",{
                                "is-invalid":errors.description})} placeholder="Project Description" name="description"
                            value={this.state.description}   onChange={this.onChange}>

                            </textarea>
                            {errors.description &&  (<div className="invalid-feedback">{errors.description}</div>)}
                        </div>
                        <h6>Start Date</h6>
                        <div className="form-group">
                            <input type="date" className="form-control form-control-lg" name="create_date"
                            value={this.state.create_date}  onChange={this.onChange} />
                            <p>{errors.create_date}</p>
                        </div>
                        <h6>Estimated End Date</h6>
                        <div className="form-group">
                            <input type="date" className="form-control form-control-lg" name="upate_date"
                            value={this.state.upate_date}  onChange={this.onChange}/>
                            <p>{errors.upate_date}</p>
                        </div>

                        <input type="submit" className="btn btn-primary btn-block mt-4" />
                    </form>
                </div>
            </div>
        </div>
    </div>
    </div>




        )
    }
 }
AddProject.propTypes=
{
    createProject: PropType.func.isRequired,
    errors:PropType.object.isRequired
}
const  mapStateToProps = state  => ({
       errors:state.errors
})
 
export default connect(mapStateToProps,{createProject}) (AddProject);